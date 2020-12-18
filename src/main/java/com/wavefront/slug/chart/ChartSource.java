package com.wavefront.slug.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Internal POJO represents a a chart source with RISON compatible property name, used in {@link
 * ChartSlug}.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@AllArgsConstructor
@JsonPropertyOrder(alphabetic = true)
class ChartSource {
  @JsonProperty("n")
  private final String queryName;

  @JsonProperty("q")
  private final String query;

  @JsonProperty("d")
  @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = BooleanFilter.class)
  @Builder.Default
  private final boolean disabled = false;

  @JsonProperty("qb")
  @Builder.Default
  private final String queryBuilderSerialization = null;

  @JsonProperty("qbe")
  @Builder.Default
  private final boolean queryBuilderEnabled = false;

  /**
   * filter which filters out false boolean field, used to omit not-disabled field by default.
   */
  private static class BooleanFilter {
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return true;
      }
      return Objects.equals(Boolean.FALSE, obj);
    }
  }
}
