package com.pporto.eventadm.interfaces.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/infra", produces = MediaType.APPLICATION_JSON_VALUE)
public class InfraStatusController {

  @GetMapping("/status")
  public InfraStatusResponse status() {
    return new InfraStatusResponse("UP");
  }

  public record InfraStatusResponse(String status) {
  }
}
