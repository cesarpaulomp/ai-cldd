package com.pporto.eventadm.infra.config;

import com.pporto.eventadm.application.ports.ApplicationRepositoryGateway;
import com.pporto.eventadm.application.usecases.ValidateApplicationClientSecret;
import com.pporto.eventadm.infra.persistence.jparepository.ApplicationClientJpaRepository;
import com.pporto.eventadm.infra.persistence.jparepository.adapter.ApplicationRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationUseCaseConfig {

  @Bean
  public ApplicationRepositoryGateway applicationRepositoryGateway(
      ApplicationClientJpaRepository applicationClientJpaRepository) {
    return new ApplicationRepositoryAdapter(applicationClientJpaRepository);
  }

  @Bean
  public ValidateApplicationClientSecret validateApplicationClientSecret(
      ApplicationRepositoryGateway applicationRepositoryGateway,
      PasswordEncoder passwordEncoder) {
    return new ValidateApplicationClientSecret(applicationRepositoryGateway, passwordEncoder);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}