package de.samples.wc.httpclient.registration;

import de.samples.wc.httpclient.HttpClient;
import de.samples.wc.webclient.WebClientAutoConfiguration;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class HttpClientBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
  public HttpClientBeanDefinitionScanner(BeanDefinitionRegistry registry) {
    super(registry, false);
    addIncludeFilter(new AnnotationTypeFilter(HttpClient.class));
    // maybe, we could configure a custom BeanNameGenerator
  }

  @Override
  protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
    AnnotationMetadata metadata = beanDefinition.getMetadata();
    return metadata.isInterface() && !metadata.isAnnotation();
  }

  @Override
  protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
    beanDefinition.setBeanClassName(HttpClientFactoryBean.class.getName());

    beanDefinition
      .getConstructorArgumentValues()
      .addGenericArgumentValue(((AnnotatedBeanDefinition) beanDefinition).getMetadata());

    beanDefinition.setDependsOn(WebClientAutoConfiguration.WEB_CLIENT_NAME);
  }

}
