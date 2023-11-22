package de.samples.wc.httpclient;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Annotate a
 * <a href="https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-interface">
 * HTTP client interface
 * </a>
 * with this annotation to get an instance that can be injected into your code.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface HttpClient {

  /**
   * The value may indicate a suggestion for a logical component name,
   * to be turned into a Spring bean in case of an autodetected component.
   *
   * @return the suggested component name, if any (or empty String otherwise)
   */
  @AliasFor(annotation = Component.class)
  String value() default "";

}
