package com.wavefront.slug.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Internal POJO represents a time range with RISON compatible property name, used in {@link
 * ChartSlug}.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class TimeRange {
  @JsonProperty("s")
  private final long startTime;

  @JsonProperty("d")
  private final long duration;

  @JsonProperty("g")
  @Builder.Default
  private final String granularity = "auto";

  @JsonProperty("c")
  @Builder.Default
  private final String compare = "off";
}
