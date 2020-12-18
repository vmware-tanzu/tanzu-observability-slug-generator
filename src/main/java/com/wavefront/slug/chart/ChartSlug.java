package com.wavefront.slug.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Internal POJO with chart details and start time and end time
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
class ChartSlug {
  @JsonProperty("t")
  private final String customerId;

  @JsonProperty("c")
  private final Chart chart;

  @JsonProperty("g")
  private final TimeRange timeRange;

  @JsonProperty("h")
  @JsonInclude(JsonInclude.Include.NON_EMPTY) // included in serialized string if not empty.
  private final List<String> focusedHosts;
}
