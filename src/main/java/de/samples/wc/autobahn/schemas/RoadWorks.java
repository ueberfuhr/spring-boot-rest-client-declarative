package de.samples.wc.autobahn.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoadWorks {

  @JsonProperty("roadworks")
  private List<RoadWork> roadWorks;

}
