package com.wavefront.slug.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wavefront.slug.filters.BooleanFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Internal POJO represents a time range with RISON compatible property name, used in {@link
 * DashboardSlug}.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class TimeSection {
  @JsonProperty("s")
  private final long startTime;

  @JsonProperty("d")
  private final long duration;

  @JsonProperty("w")
  private final String windowSize;

  @JsonProperty("c")
  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  @Builder.Default
  private final String compare = "off";

  @JsonProperty("ls")
  @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = BooleanFilter.class)
  @Builder.Default
  private final boolean live = false;
}
