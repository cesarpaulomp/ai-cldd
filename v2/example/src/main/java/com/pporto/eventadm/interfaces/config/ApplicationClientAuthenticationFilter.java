package com.pporto.eventadm.interfaces.config;

import com.pporto.eventadm.application.usecases.ValidateApplicationClientSecret;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.UUID;

@Component
public class ApplicationClientAuthenticationFilter extends OncePerRequestFilter {

  private static final String BASIC_PREFIX = "Basic ";

  private final ValidateApplicationClientSecret validateApplicationClientSecret;

  public ApplicationClientAuthenticationFilter(ValidateApplicationClientSecret validateApplicationClientSecret) {
    this.validateApplicationClientSecret = validateApplicationClientSecret;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (isPublicPath(request.getRequestURI())) {
      filterChain.doFilter(request, response);
      return;
    }

    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader == null || !authorizationHeader.startsWith(BASIC_PREFIX)) {
      writeUnauthorized(response);
      return;
    }

    String encodedCredentials = authorizationHeader.substring(BASIC_PREFIX.length());
    String decodedCredentials;

    try {
      decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), StandardCharsets.UTF_8);
    } catch (IllegalArgumentException exception) {
      writeUnauthorized(response);
      return;
    }

    String[] credentialsParts = decodedCredentials.split(":", 2);
    if (credentialsParts.length != 2) {
      writeUnauthorized(response);
      return;
    }

    UUID applicationClientId;
    try {
      applicationClientId = UUID.fromString(credentialsParts[0]);
    } catch (IllegalArgumentException exception) {
      writeUnauthorized(response);
      return;
    }

    boolean isValid = validateApplicationClientSecret.execute(credentialsParts[1], applicationClientId);
    if (!isValid) {
      writeUnauthorized(response);
      return;
    }

    Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(
        applicationClientId.toString(),
        null,
        Collections.emptyList());

    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(authentication);
    SecurityContextHolder.setContext(securityContext);

    filterChain.doFilter(request, response);
  }

  private boolean isPublicPath(String path) {
    return path.equals("/v1/infra/status")
        || path.startsWith("/v3/api-docs")
        || path.startsWith("/swagger-ui")
        || path.equals("/swagger-ui.html");
  }

  private void writeUnauthorized(HttpServletResponse response) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write("{\"error\":\"UNAUTHORIZED\"}");
  }
}