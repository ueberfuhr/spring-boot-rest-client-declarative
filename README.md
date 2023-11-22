# Spring Boot Rest API Client Sample

In this project, we call the [Autobahn App API](https://autobahn.api.bund.dev) using Spring Boot.
We have multiple possibilities:

- use the [RestTemplate](https://www.baeldung.com/rest-template) (MVC)
- use the [WebClient](https://www.baeldung.com/spring-5-webclient) (WebFlux)
- use the [HTTP interface](https://www.baeldung.com/spring-6-http-interface) (both MVC and WebFlux)

In this project, we use the HTTP interface with Spring WebFlux 
(see interface [RoadsClient](src/main/java/de/samples/wc/autobahn/RoadsClient.java)):

```java
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import reactor.core.publisher.Mono;

public interface RoadsClient {

  @GetExchange("/{road}/services/roadworks")
  Mono<RoadWorks> getRoadWorks(@PathVariable String road);

}
```

## Declarative Http Interface

Unfortunately, we have to register the HTTP interface programmatically with `@Bean`.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RoadsClientConfiguration {
  @Bean
  public RoadsClient roadsClient(WebClient webClient) {
    final var adapter = WebClientAdapter
            .forClient(webClient);
    final var factory = HttpServiceProxyFactory
            .builder(adapter)
            .build();
    return factory.createClient(RoadsClient.class);
  }
}
```

In this project, we have a mechanism to allow declarative HTTP interface registration,
a simplified solution based on the idea of [Pavel Grigorev](https://github.com/pavel-grigorev/spring-icomponent).

For this, we just need to add `@EnableHttpClients` to the application class, so we could
register the Http interface without any boilerplate code by using this annotation:

```java
import de.samples.wc.httpclient.HttpClient;

@HttpClient
public interface RoadsClient {

    // ...
  
}

```

## Run the application

Just run the application and call `GET http://localhost:8080/roadworks/A5` to get all roadworks
that are currently existing on the autobahn `A5`.
