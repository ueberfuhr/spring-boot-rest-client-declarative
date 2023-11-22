package de.samples.wc.httpclient;

import de.samples.wc.httpclient.registration.HttpClientBeansRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Runs component scanning targeted at Http Client interfaces.
 * The annotation is {@link HttpClient}..
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HttpClientBeansRegistrar.class)
public @interface EnableHttpClients {
  /**
   * Alias for {@link #basePackages()}.
   */
  @AliasFor("basePackages")
  String[] value() default {};

  /**
   * Packages to scan.
   */
  @AliasFor("value")
  String[] basePackages() default {};

  /**
   * The package of each class will be scanned.
   */
  Class<?>[] basePackageClasses() default {};

}
