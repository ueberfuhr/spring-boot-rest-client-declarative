package de.samples.wc.webclient;

import io.netty.channel.ChannelOption;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(WebClientConfig.class)
public class WebClientAutoConfiguration {

  public static final String WEB_CLIENT_NAME = "webClient";

  @Bean(WEB_CLIENT_NAME)
  WebClient webClient(WebClientConfig config) {
    HttpClient httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.timeout())
      .responseTimeout(Duration.ofMillis(config.timeout()));
    return WebClient.builder()
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .baseUrl(config.baseUrl())
      .build();
  }

}
