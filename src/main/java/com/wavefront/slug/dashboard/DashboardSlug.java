/*
Tanzu Observability Slug Generator
Copyright 2020 VMware, Inc.
This product is licensed to you under the Apache 2.0 license (the "License").  You may not use this
product except in compliance with the Apache 2.0 License.
This product may include a number of subcomponents with separate copyright notices and license terms.
Your use of these subcomponents is subject to the terms and conditions of the subcomponent's license,
as noted in the LICENSE file.
*/
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
