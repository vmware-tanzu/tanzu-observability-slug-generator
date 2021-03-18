/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.dashboard;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.net.UrlEscapers;

import com.bazaarvoice.jackson.rison.RisonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.ReadableInstant;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder Implementation for {@link DashboardSlugBuilder} using
 * {@link RisonFactory Jackson RISON Lib} (https://github.com/bazaarvoice/rison).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
class DashboardSlugBuilderImpl implements DashboardSlugBuilder {
  private static final String SELECTED_VARIABLE_PREFIX = "Label";

  // RISON mapper to serialize into RISON format
  private static final ObjectMapper mapper = new ObjectMapper(new RisonFactory());

  private final Map<String, DashboardVariable> dashboardVariables = new HashMap<>();

  private Long start;
  private Long end;
  private String windowSize;
  private String compare;
  private boolean enableLiveRefresh = false;

  @Override
  public DashboardSlugBuilder setStart(long startMillis) {
    this.start = startMillis;
    return this;
  }

  @Override
  public DashboardSlugBuilder setStart(ReadableInstant instant) {
    this.start = instant.getMillis();
    return this;
  }

  @Override
  public DashboardSlugBuilder setEnd(long endMillis) {
    this.end = endMillis;
    return this;
  }

  @Override
  public DashboardSlugBuilder setEnd(ReadableInstant instant) {
    this.end = instant.getMillis();
    return this;
  }

  @Override
  public DashboardSlugBuilder setLiveRefresh(boolean enableLiveRefresh) {
    this.enableLiveRefresh = enableLiveRefresh;
    return this;
  }

  @Override
  public DashboardSlugBuilder setWindowSize(String windowSize) {
    this.windowSize = windowSize;
    return this;
  }

  @Override
  public DashboardSlugBuilder setCompare(String compare) {
    this.compare = compare;
    return this;
  }

  @Override
  public DashboardSlugBuilder setSimpleDashboardVariable(String name, String value) {
    // Jackson does not add the value into the serialized string if the value is null.
    DashboardVariable variable = DashboardVariable.builder().value(value).build();
    this.dashboardVariables.put(name, variable);
    return this;
  }

  @Override
  public DashboardSlugBuilder setListDashboardVariable(String name, String selected) {
    // Jackson does not add the value into the serialized string if the value is null.
    DashboardVariable variable = DashboardVariable.builder().selected(selected).build();
    this.dashboardVariables.put(name, variable);
    return this;
  }

  @Override
  public DashboardSlugBuilder setDynamicDashboardVariable(String name, String value) {
    // Jackson does not add the value into the serialized string if the value is null.
    DashboardVariable variable = DashboardVariable.builder().value(value).build();
    this.dashboardVariables.put(name, variable);
    return this;
  }

  @Override
  public String build() {
    Preconditions.checkState(start != null, "start must be set");
    Preconditions.checkState(end != null, "end must be set");

    try {
      return mapper.writeValueAsString(toDashboardSlug());
    } catch (JsonProcessingException e) {
      throw Throwables.propagate(e);
    }
  }

  @Override
  public String buildAndEscape() {
    String slug = build();
    return UrlEscapers.urlFragmentEscaper().escape(slug);
  }

  private DashboardSlug toDashboardSlug() {
    return DashboardSlug.builder().
        timeRange(TimeSection.builder().
            startTime((long) Math.floor(this.start / 1000.0)).
            duration((long) Math.floor((this.end - this.start) / 1000.0)).
            live(this.enableLiveRefresh).
            windowSize(this.windowSize).
            compare(this.compare).
            build()).
        parameters(this.dashboardVariables).
        build();
  }
}
