package com.pporto.eventadm.application.usecases;

import com.pporto.eventadm.application.ports.ApplicationRepositoryGateway;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class ValidateApplicationClientSecret {

  private final ApplicationRepositoryGateway applicationRepositoryGateway;
  private final PasswordEncoder passwordEncoder;

  public ValidateApplicationClientSecret(
      ApplicationRepositoryGateway applicationRepositoryGateway,
      PasswordEncoder passwordEncoder) {
    this.applicationRepositoryGateway = applicationRepositoryGateway;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean execute(String clientSecret, UUID applicationClientId) {
    if (clientSecret == null || clientSecret.isBlank() || applicationClientId == null) {
      return false;
    }

    return applicationRepositoryGateway.findById(applicationClientId)
        .map(applicationClient -> passwordEncoder.matches(clientSecret, applicationClient.clientSecretHash()))
        .orElse(false);
  }
}