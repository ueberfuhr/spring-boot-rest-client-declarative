package de.samples.wc.httpclient.util;

import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DefaultPackageResolver implements PackageResolver {
  private Set<String> result;

  protected String getBasePackagesAttributeName() {
    return "basePackages";
  }

  protected String getBasePackageClassesAttributeName() {
    return "basePackageClasses";
  }

  @Override
  public String[] getPackageNames(MergedAnnotation<?> annotation, String className) {
    result = new LinkedHashSet<>();

    collectFromAnnotation(annotation);

    if (result.isEmpty()) {
      collectFromClassName(className);
    }

    return result.toArray(new String[0]);
  }

  private void collectFromAnnotation(MergedAnnotation<?> annotation) {
    AnnotationAttributes<?> attributes = AnnotationAttributes.of(annotation);

    collectFromBasePackages(attributes);
    collectFromBasePackageClasses(attributes);
  }

  private void collectFromBasePackages(AnnotationAttributes<?> attributes) {
    String attributeName = getBasePackagesAttributeName();

    if (attributeName.isBlank()) {
      return;
    }

    attributes
      .getStrings(attributeName)
      .ifPresent(result::addAll);
  }

  private void collectFromBasePackageClasses(AnnotationAttributes<?> attributes) {
    String attributeName = getBasePackageClassesAttributeName();

    if (attributeName.isBlank()) {
      return;
    }

    attributes
      .getClasses(attributeName)
      .ifPresent(this::collectFromClasses);
  }

  private void collectFromClasses(List<Class<?>> classes) {
    classes
      .stream()
      .map(ClassUtils::getPackageName)
      .forEach(result::add);
  }

  private void collectFromClassName(String className) {
    result.add(ClassUtils.getPackageName(className));
  }
}
