/*
Tanzu Observability Slug Generator
Copyright 2020 VMware, Inc.

This product is licensed to you under the Apache 2.0 license (the "License").  You may not use this
product except in compliance with the Apache 2.0 License.

This product may include a number of subcomponents with separate copyright notices and license terms.
Your use of these subcomponents is subject to the terms and conditions of the subcomponent's license,
as noted in the LICENSE file.
*/
package com.wavefront.slug.chart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wavefront.slug.filters.BooleanFilter;

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
}
