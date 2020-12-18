package com.wavefront.slug;

import com.wavefront.slug.chart.ChartSlugBuilder;
import com.wavefront.slug.chart.ChartSlugBuilders;

public final class SlugBuilders {

  private SlugBuilders() {
    throw new UnsupportedOperationException("SlugBuilders is an static factory class, cannot be" +
        " instantiated.");
  }

  public static ChartSlugBuilder chartSlugBuilder() {
    return ChartSlugBuilders.chartSlugBuilder();
  }
}
