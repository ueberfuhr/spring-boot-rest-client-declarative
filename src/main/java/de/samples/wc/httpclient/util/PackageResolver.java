package de.samples.wc.httpclient.util;

import org.springframework.core.annotation.MergedAnnotation;

public interface PackageResolver {
  String[] getPackageNames(MergedAnnotation<?> annotation, String className);
}
