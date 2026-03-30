package com.pporto.eventadm.application.ports;

import com.pporto.eventadm.domain.entities.ApplicationClient;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationRepositoryGateway {

  Optional<ApplicationClient> findById(UUID applicationClientId);
}