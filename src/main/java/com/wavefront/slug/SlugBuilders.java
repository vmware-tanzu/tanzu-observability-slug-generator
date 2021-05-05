/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug;

import com.wavefront.slug.chart.ChartSlugBuilder;
import com.wavefront.slug.chart.ChartSlugBuilders;
import com.wavefront.slug.dashboard.DashboardSlugBuilder;
import com.wavefront.slug.dashboard.DashboardSlugBuilders;

import lombok.experimental.UtilityClass;

/**
 * Static factory to generate different slug builders:
 *
 * 1. Use {@link ChartSlugBuilder} to build a Wavefront chart slug.
 * 2. Use {@link DashboardSlugBuilder} to build a Wavefront dashboard slug.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@UtilityClass
public final class SlugBuilders {
  public static ChartSlugBuilder chartSlugBuilder() {
    return ChartSlugBuilders.slugBuilder();
  }

  @Deprecated
  public static ChartSlugBuilder chartSlugBuilderV1() {
    return ChartSlugBuilders.slugBuilderV1();
  }

  public static DashboardSlugBuilder dashboardSlugBuilder() {
    return DashboardSlugBuilders.slugBuilder();
  }
}
