/*
Tanzu Observability Slug Generator
Copyright 2020 VMware, Inc.

This product is licensed to you under the Apache 2.0 license (the "License"). You may not use this
product except in compliance with the Apache 2.0 License.

This product may include a number of subcomponents with separate copyright notices and license terms.
Your use of these subcomponents is subject to the terms and conditions of the subcomponent's license,
as noted in the LICENSE file.
*/

package com.wavefront.slug.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavefront.slug.filters.BooleanFilter;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Internal Pojo for a dashboard variable.
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@Builder
@AllArgsConstructor
class DashboardVariable {
  @JsonProperty("l")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final String label;

  @JsonProperty("d")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final String defaultValue;

  @JsonProperty("v")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final String value;

  @JsonProperty("m")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final Map<String, String> variableDetails;

  @JsonProperty("s")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final String selected;

  @JsonProperty("vo")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final List<String> valueOrdering;

  @JsonProperty("k")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final String tagKey;

  @JsonProperty("f")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final String field;

  @JsonProperty("q")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final String query;

  @JsonProperty("h")
  @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = BooleanFilter.class)
  @Builder.Default
  private final boolean hidden = false;

  @JsonProperty("ala")
  @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = BooleanFilter.class)
  @Builder.Default
  private final boolean allowAll = false;
}
