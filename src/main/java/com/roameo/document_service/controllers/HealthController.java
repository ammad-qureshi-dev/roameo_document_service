package com.roameo.document_service.controllers;

import static com.roameo.document_service.utils.Constants.Endpoints.BASE_URL;
import static com.roameo.response.ServiceResponse.getServiceResponse;

import com.roameo.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BASE_URL + "/health")
public class HealthController {

  @GetMapping("/ping")
  public ResponseEntity<ServiceResponse<String>> ping() {
    return getServiceResponse(true, "pong", HttpStatus.OK);
  }

  @GetMapping("/connection-checkup")
  public ResponseEntity<ServiceResponse<String>> connectionCheckup() {
    // ToDo: ping database and redis clusters to ensure if everything is working fine
    var isConnectionSuccessful = true;
    return getServiceResponse(
        isConnectionSuccessful,
        isConnectionSuccessful ? "up" : "down",
        isConnectionSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }
}
