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
