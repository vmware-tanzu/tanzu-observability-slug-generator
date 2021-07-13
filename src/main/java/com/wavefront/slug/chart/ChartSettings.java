/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.chart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * Internal POJO represents a chart's settings with RISON compatible property name, used in {@link Chart}.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@Jacksonized
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChartSettings {
  // one of "line", "scatterplot", "table", "scatterplot-xy" or "markdown-widget", "sparkline"
  @JsonProperty
  @Builder.Default
  private final String type = "line";

  @JsonProperty
  private final Double max;
  @JsonProperty
  private final Double min;

  @JsonProperty
  private final String lineType;
  @JsonProperty
  private final String stackType;

  @JsonProperty
  private final Long expectedDataSpacing;

  @JsonProperty
  private final String windowing;
  @JsonProperty
  private final Long windowSize;

  @JsonProperty
  @Builder.Default
  private final Boolean showHosts = true;
  @JsonProperty
  @Builder.Default
  private final Boolean showLabels = true;
  @JsonProperty
  @Builder.Default
  private final Boolean showRawValues = false;

  // one of "all", "top", "custom"
  @JsonProperty
  @Builder.Default
  private final String tagMode = "all";
  @JsonProperty
  @Builder.Default
  private final Integer numTags = 4;
  @JsonProperty
  private final List<String> customTags;

  @JsonProperty
  private final Boolean groupBySource;

  // table
  @JsonProperty
  private final Boolean sortValuesDescending;
  @JsonProperty
  private final String defaultSortColumn;

  @JsonProperty
  private final Double y1Max;
  @JsonProperty
  private final Double y1Min;
  @JsonProperty
  private final String y1Units;

  @JsonProperty
  private final Boolean y0ScaleSIBy1024;
  @JsonProperty
  private final Boolean y1ScaleSIBy1024;
  @JsonProperty
  private final Boolean y0UnitAutoscaling;
  @JsonProperty
  private final Boolean y1UnitAutoscaling;

  @JsonProperty
  private final Boolean invertDynamicLegendHoverControl;

  // legend
  @JsonProperty
  private final Boolean fixedLegendEnabled;
  @JsonProperty
  private final Boolean fixedLegendUseRawStats;
  @JsonProperty
  private final String fixedLegendPosition;
  @JsonProperty
  private final List<String> fixedLegendDisplayStats;
  @JsonProperty
  private final String fixedLegendFilterSort;
  @JsonProperty
  private final Boolean fixedLegendShowSourceName;
  @JsonProperty
  private final Boolean fixedLegendShowMetricName;
  @JsonProperty
  private final Integer fixedLegendFilterLimit;
  @JsonProperty
  private final String fixedLegendFilterField;

  // scatterplot XY
  @JsonProperty
  private final Double xMax;
  @JsonProperty
  private final Double xMin;
  @JsonProperty
  private final Double yMax;
  @JsonProperty
  private final Double yMin;
  @JsonProperty
  private final Boolean timeBasedColoring;

  // text widgets
  @JsonProperty
  private final String markdownContent;
  @JsonProperty
  private final String plainMarkdownContent;

  // sparkline
  @JsonProperty
  private final String sparklineDisplayValueType;
  @JsonProperty
  private final String sparklineDisplayColor;
  @JsonProperty
  private final String sparklineDisplayVerticalPosition;
  @JsonProperty
  private final String sparklineDisplayHorizontalPosition;
  @JsonProperty
  private final String sparklineDisplayFontSize;
  @JsonProperty
  private final String sparklineDisplayPrefix;
  @JsonProperty
  private final String sparklineDisplayPostfix;
  @JsonProperty
  private final String sparklineSize;
  @JsonProperty
  private final String sparklineLineColor;
  @JsonProperty
  private final String sparklineFillColor;
  @JsonProperty
  private final List<String> sparklineValueColorMapColors;
  @JsonProperty
  private final List<Double> sparklineValueColorMapValuesV2;

  // we expect this to form a chain of [ color | value | color | value | color ] (so colors should
  // always have 1 more element than values)
  @JsonProperty
  private final String sparklineValueColorMapApplyTo;
  @JsonProperty
  private final Integer sparklineDecimalPrecision;

  @JsonProperty
  private final List<String> sparklineValueTextMapText;
  @JsonProperty
  private final List<Double> sparklineValueTextMapThresholds;

  @JsonProperty
  private final String chartDefaultColor;
}
