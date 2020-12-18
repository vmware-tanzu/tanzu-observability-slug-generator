package com.wavefront.slug.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Internal POJO represents a a chart with RISON compatible property name, used in {@link ChartSlug}.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Chart {
  @JsonProperty("id")
  @Builder.Default
  private final String id = "chart";

  @JsonProperty("n")
  @Builder.Default
  private final String name = "Chart";

  @JsonProperty("ne")
  @Builder.Default
  private final boolean displaySourceEvents = true;

  @JsonProperty("smp")
  @Builder.Default
  private final String sampling = "off";

  @JsonProperty("u")
  private final String units;

  @JsonProperty("b")
  @Builder.Default
  private final int base = 1;

  @JsonProperty("s")
  @Builder.Default
  private final List<ChartSource> chartSources = Collections.emptyList();
}
