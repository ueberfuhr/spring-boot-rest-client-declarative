package de.samples.wc.autobahn;

import de.samples.wc.autobahn.schemas.RoadWorks;
import de.samples.wc.httpclient.HttpClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

@HttpClient
public interface RoadsClient {

  @GetExchange("/{road}/services/roadworks")
  Mono<RoadWorks> getRoadWorks(@PathVariable String road);

}
