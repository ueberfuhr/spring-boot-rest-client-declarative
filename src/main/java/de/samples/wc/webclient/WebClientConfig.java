package de.samples.wc.webclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.webclient")
public record WebClientConfig(
  String baseUrl,
  int timeout
) {
}
