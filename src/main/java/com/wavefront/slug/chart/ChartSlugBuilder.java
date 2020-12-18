package com.wavefront.slug.chart;

import org.joda.time.ReadableInstant;

/**
 * Interface of {@link ChartSlugBuilderImpl}
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public interface ChartSlugBuilder {
  /**
   * Set the customer id for the chart.
   *
   * @param customerId Customer Id for the chart.
   * @return The builder.
   */
  ChartSlugBuilder setCustomerId(String customerId);

  /**
   * Set the chart id. This is technically used only when redirected from a dashboard and defaults
   * to "chart".
   *
   * @param id The id for the chart.
   * @return The builder.
   */
  ChartSlugBuilder setId(String id);

  /**
   * Set the name of the chart.
   *
   * @param name Name of the chart.
   * @return The builder.
   */
  ChartSlugBuilder setName(String name);

  /**
   * Set the start millis.
   *
   * @param startMillis Start millis.
   * @return The builder.
   */
  ChartSlugBuilder setStart(long startMillis);

  /**
   * Set the start instant.
   *
   * @param instant The start instant.
   * @return The builder.
   */
  ChartSlugBuilder setStart(ReadableInstant instant);

  /**
   * Set the end millis.
   *
   * @param endMillis End millis.
   * @return The builder.
   */
  ChartSlugBuilder setEnd(long endMillis);

  /**
   * Set the end millis.
   *
   * @param instant The end instant.
   * @return The builder.
   */
  ChartSlugBuilder setEnd(ReadableInstant instant);

  /**
   * Set the granularity of the entire chart. Valid values are determined by the FE. Possible values
   * include: "m", "h", "d" and "auto". Defaults to "auto".
   *
   * @param granularity The granularity to use.
   * @return The builder.
   */
  ChartSlugBuilder setGranularity(String granularity);

  /**
   * Sets the comparison option for the entire chart. Valid values are determined by the FE.
   * Possible values include: "1d", "1w", "1m". Defaults to null.
   *
   * @param compare Comparison option to set for the chart.
   * @return The builder.
   */
  ChartSlugBuilder setCompare(String compare);

  /**
   * Units to use for the chart (rendered to the right of the Y-axis).
   *
   * @param units Units to use for the chart.
   * @return The builder.
   */
  ChartSlugBuilder setUnits(String units);

  /**
   * Sets the base of the y-axis of the chart. Value must be >= 1. A value of 1 indicates that a
   * linear y-axis should be used.
   *
   * @param base Y-axis base.
   * @return The builder.
   */
  ChartSlugBuilder setBase(int base);

  /**
   * Add a new source query to the chart.
   *
   * @param name  Name of the query.
   * @param query The query.
   * @return The builder.
   */
  ChartSlugBuilder addSource(String name, String query);

  /**
   * Add a new source query to the chart.
   *
   * @param name     Name of the query.
   * @param query    The query.
   * @param disabled True if this query should be disabled (unchecked) in the chart.
   * @return The builder.
   */
  ChartSlugBuilder addSource(String name, String query, boolean disabled);

  /**
   * Add a new source query to the chart.
   *
   * @param name                      Name of the query.
   * @param query                     The query.
   * @param disabled                  True if this query should be disabled (unchecked) in the
   *                                  chart.
   * @param queryBuilderSerialization Serialization of the querybuilder
   * @param queryBuilderEnabled       True if the querybuilder is enabled
   * @return The builder.
   */
  ChartSlugBuilder addSource(String name, String query, boolean disabled, String queryBuilderSerialization,
                             boolean queryBuilderEnabled);

  /**
   * Add a host that is focused upon when the page loads. The chart must contain queries that yield
   * host tags (e.g. host=abcd01 or host=*) for focusing to work.
   *
   * @param hostName Host name for series tagged by it to be focused on.
   * @return The builder.
   */
  ChartSlugBuilder addFocusedHost(String hostName);

  /**
   * Build the chart slug.
   *
   * @return The chart slug.
   */
  String build();

  /**
   * Build and encoded the chart slug to be used in the chart url.
   */
  String buildAndEncode();
}
