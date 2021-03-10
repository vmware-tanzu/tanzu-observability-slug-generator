/*
Tanzu Observability Slug Generator
Copyright 2020 VMware, Inc.

This product is licensed to you under the Apache 2.0 license (the "License").  You may not use this
product except in compliance with the Apache 2.0 License.

This product may include a number of subcomponents with separate copyright notices and license terms.
Your use of these subcomponents is subject to the terms and conditions of the subcomponent's license,
as noted in the LICENSE file.
*/
package com.wavefront.slug;

import com.wavefront.slug.chart.ChartSlugBuilder;
import com.wavefront.slug.chart.ChartSlugBuilders;
import com.wavefront.slug.dashboard.DashboardSlugBuilder;
import com.wavefront.slug.dashboard.DashboardSlugBuilders;

/**
 * Static factory to generate different slug builders:
 *
 * 1. Use {@link ChartSlugBuilder} to build a Wavefront chart slug.
 * 2. Use {@link DashboardSlugBuilder} to build a Wavefront dashboard slug.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public final class SlugBuilders {

  private SlugBuilders() {
    throw new UnsupportedOperationException("SlugBuilders is an static factory class, cannot be" +
        " instantiated.");
  }

  public static ChartSlugBuilder chartSlugBuilder() {
    return ChartSlugBuilders.slugBuilder();
  }

  public static DashboardSlugBuilder dashboardSlugBuilder() {
    return DashboardSlugBuilders.slugBuilder();
  }
}
