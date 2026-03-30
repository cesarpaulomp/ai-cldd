package com.pporto.eventadm.infra.persistence.jparepository.adapter;

import com.pporto.eventadm.application.ports.ApplicationRepositoryGateway;
import com.pporto.eventadm.domain.entities.ApplicationClient;
import com.pporto.eventadm.infra.persistence.jpaentity.ApplicationClientJpaEntity;
import com.pporto.eventadm.infra.persistence.jparepository.ApplicationClientJpaRepository;

import java.util.Optional;
import java.util.UUID;

public class ApplicationRepositoryAdapter implements ApplicationRepositoryGateway {

  private final ApplicationClientJpaRepository applicationClientJpaRepository;

  public ApplicationRepositoryAdapter(ApplicationClientJpaRepository applicationClientJpaRepository) {
    this.applicationClientJpaRepository = applicationClientJpaRepository;
  }

  @Override
  public Optional<ApplicationClient> findById(UUID applicationClientId) {
    return applicationClientJpaRepository.findById(applicationClientId)
        .map(this::toDomain);
  }

  private ApplicationClient toDomain(ApplicationClientJpaEntity jpaEntity) {
    return new ApplicationClient(
        jpaEntity.getId(),
        jpaEntity.getName(),
        jpaEntity.getClientSecret(),
        jpaEntity.getCreatedAt(),
        jpaEntity.getUpdatedAt());
  }
}