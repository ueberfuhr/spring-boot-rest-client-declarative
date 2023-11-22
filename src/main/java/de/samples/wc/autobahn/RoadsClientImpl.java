package de.samples.wc.autobahn;

import de.samples.wc.autobahn.schemas.RoadWorks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @deprecated Use {@link RoadsClient} instead.
 */
@Component
@RequiredArgsConstructor
@Deprecated
public class RoadsClientImpl {

  private final WebClient webClient;

  public Mono<RoadWorks> getRoadWorks(String road) {
    return webClient
      .get()
      .uri(uriBuilder -> uriBuilder
        .path("/{roadId}/services/roadworks")
        .build(road)
      )
      .retrieve()
      .bodyToMono(RoadWorks.class);
  }

}
