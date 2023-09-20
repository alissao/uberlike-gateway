package com.vehicletrackergateway.uberlike.interfaces.http;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletrackergateway.uberlike.infrastructure.config.YAMLConfig;

import reactor.core.publisher.Mono;

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
  public Mono<String> ping() {
    return Mono.just("pong");
  }

  @GetMapping("/getConfig")
  public Mono<String> getConfig() {
    return Mono.just(yamlConfig.getWorld());
  }

  @GetMapping("hello")
	Mono<String> hello(@AuthenticationPrincipal Principal auth) {
		return Mono.just(String.format("Hello %s!", auth.getName()));
	}
  
}
