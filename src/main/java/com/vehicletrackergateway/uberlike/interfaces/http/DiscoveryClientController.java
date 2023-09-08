package com.vehicletrackergateway.uberlike.interfaces.http;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletrackergateway.uberlike.infrastructure.config.YAMLConfig;

@RestController
public class DiscoveryClientController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private YAMLConfig yamlConfig;

  public Optional<URI> serviceUrl() {
    return discoveryClient.getInstances("uberlike")
      .stream()
      .map(si -> si.getUri())
      .findFirst();
  }

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/getConfig")
  public String getConfig() {
    return yamlConfig.getWorld();
  }
  
}
