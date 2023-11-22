package de.samples.wc.autobahn;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * We can use this configuration to programmatically create the {@link RoadsClient},
 * but we have a mechanism to create a HttpClient interface with @{@link de.samples.wc.httpclient.HttpClient}.
 *
 * @see de.samples.wc.httpclient.EnableHttpClients
 */
//@Configuration
@Deprecated
public class RoadsClientConfiguration {

  //   @Bean
  public RoadsClient roadsClient(WebClient webClient) {
    final var adapter = WebClientAdapter
      .forClient(webClient);
    final var factory = HttpServiceProxyFactory.builder(adapter)
      .build();
    return factory.createClient(RoadsClient.class);
  }

}
