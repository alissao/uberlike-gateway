package com.vehicletrackergateway.uberlike.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@RefreshScope
@Configuration
@ConfigurationProperties("hello")
@Data
public class YAMLConfig {
  
  private String world;

}
