package de.samples.wc;

import de.samples.wc.autobahn.RoadsClient;
import de.samples.wc.autobahn.schemas.RoadWork;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/roadworks")
@RequiredArgsConstructor
public class RoadWorksController {

  private final RoadsClient roadsClient;

  @GetMapping("/{road}")
  public Flux<RoadWork> findRoadWorks(@PathVariable String road) {
    return roadsClient
      .getRoadWorks(road)
      .flatMapMany(roadworks -> Flux.fromStream(roadworks.getRoadWorks().stream()));
  }


}
