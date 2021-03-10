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
