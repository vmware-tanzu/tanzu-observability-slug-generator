package com.wavefront.slug.chart;

public final class ChartSlugBuilders {
  private ChartSlugBuilders() {
    throw new UnsupportedOperationException("ChartSlugBuilders is an static factory class, cannot be" +
        " instantiated.");
  }

  public static ChartSlugBuilder chartSlugBuilder() {
    return new ChartSlugBuilderImpl();
  }
}
