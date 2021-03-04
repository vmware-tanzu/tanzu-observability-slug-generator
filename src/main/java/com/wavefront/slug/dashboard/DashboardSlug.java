package com.wavefront.slug.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Internal POJO with dashboard details.
 *
 * This slug only represents a dashboard's configuration, such as variables' value and time section.
 * The actual dashboard details should be defined before-hand and elsewhere.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
class DashboardSlug {
  @JsonProperty("g")
  private final TimeSection timeRange;

  @JsonProperty("p")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final Map<String, DashboardVariable> parameters;
}
