package com.pporto.eventadm.domain.entities;

import java.time.Instant;
import java.util.UUID;

public record ApplicationClient(
    UUID id,
    String name,
    String clientSecretHash,
    Instant createdAt,
    Instant updatedAt) {
}