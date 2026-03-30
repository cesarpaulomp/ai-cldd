package com.pporto.eventadm.infra.persistence.jparepository;

import com.pporto.eventadm.infra.persistence.jpaentity.ApplicationClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationClientJpaRepository extends JpaRepository<ApplicationClientJpaEntity, UUID> {
}