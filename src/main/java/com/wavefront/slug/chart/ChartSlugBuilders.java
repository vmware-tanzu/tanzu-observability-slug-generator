/*
Tanzu Observability Slug Generator
Copyright 2020 VMware, Inc.

This product is licensed to you under the Apache 2.0 license (the "License").  You may not use this
product except in compliance with the Apache 2.0 License.

This product may include a number of subcomponents with separate copyright notices and license terms.
Your use of these subcomponents is subject to the terms and conditions of the subcomponent's license,
as noted in the LICENSE file.
*/
package com.wavefront.slug.chart;

/**
 * Static factories for Chart slug builder ({@link ChartSlugBuilder}).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public final class ChartSlugBuilders {
  private ChartSlugBuilders() {
    throw new UnsupportedOperationException("ChartSlugBuilders is an static factory class, cannot be" +
        " instantiated.");
  }

  public static ChartSlugBuilder slugBuilder() {
    return new ChartSlugBuilderImpl();
  }
}
