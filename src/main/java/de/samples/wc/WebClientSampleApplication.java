package de.samples.wc;

import de.samples.wc.httpclient.EnableHttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHttpClients
public class WebClientSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebClientSampleApplication.class, args);
  }

}
