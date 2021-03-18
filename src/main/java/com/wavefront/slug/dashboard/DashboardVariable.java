/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
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
