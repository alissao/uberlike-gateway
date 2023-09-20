package com.vehicletrackergateway.uberlike.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.oauth2.jwt.*;

import reactor.core.publisher.Mono;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
  
  @Bean
  public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity, Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter) throws Exception {
    
    httpSecurity
      .csrf(ServerHttpSecurity.CsrfSpec::disable);

    httpSecurity
      .authorizeExchange((authorize) -> 
          authorize
            .pathMatchers("/actuator/health/**", "/ping", "/getConfig").permitAll()
            .anyExchange().authenticated()
      );

    // httpSecurity
    //   .oauth2Login(oauth2 -> oauth2.authorizationRequestResolver(authorizationRequestResolver(this.clientRegistrationRepository)));
      
    httpSecurity
      .oauth2Login(withDefaults());

    httpSecurity
      .oauth2Client(withDefaults());

    httpSecurity
      .oauth2ResourceServer((resourceServer) -> 
          resourceServer.jwt((jwt) -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
      );

    return httpSecurity.build();
  }

}
