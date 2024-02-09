/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.chart;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.net.UrlEscapers;

import com.bazaarvoice.jackson.rison.RisonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavefront.slug.SlugVersion;

import org.joda.time.ReadableInstant;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Builder implementation for {@link ChartSlugBuilder} using {@link RisonFactory Jackson RISON Lib}
 * (https://github.com/bazaarvoice/rison).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public class ChartSlugBuilderImpl implements ChartSlugBuilder {
  // RISON mapper to serialize into RISON format
  private static final ObjectMapper mapper = new ObjectMapper(new RisonFactory());
  // JSON mapper to deserialize chart settings / chart attributes into entities
  private static final ObjectMapper JSONMapper = new ObjectMapper();

  // slug version
  private final SlugVersion slugVersion;

  // in memory chart properties
  private final List<ChartSource> sources = Lists.newArrayList();
  private final List<String> focusedHosts = Lists.newArrayList();
  private String customerId;
  private String id = "chart";
  private String name = "Chart";
  private Long start;
  private Long end;
  private String granularity = "auto";
  private String compare = "off";
  private String units = null;
  private int base = 1;
  private String chartDescription = null;
  private ChartSettings chartSettings = null;
  private JsonNode chartAttributes = null;

  public ChartSlugBuilderImpl(SlugVersion slugVersion) {
    this.slugVersion = slugVersion;
  }

  @Override
  public ChartSlugBuilderImpl setCustomerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setStart(long startMillis) {
    this.start = startMillis;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setStart(ReadableInstant instant) {
    this.start = instant.getMillis();
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setEnd(long endMillis) {
    this.end = endMillis;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setEnd(ReadableInstant instant) {
    this.end = instant.getMillis();
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setGranularity(String granularity) {
    this.granularity = granularity;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setCompare(String compare) {
    this.compare = compare;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setUnits(String units) {
    this.units = units;
    return this;
  }

  @Override
  public ChartSlugBuilderImpl setBase(int base) {
    this.base = base;
    return this;
  }

  @Override
  public ChartSlugBuilder setDescription(String description) {
    this.chartDescription = description;
    return this;
  }

  @Override
  public ChartSlugBuilder setChartSettings(String chartSettings) {
    this.chartSettings = parseJSON(chartSettings, ChartSettings.class);
    return this;
  }

  @Override
  public ChartSlugBuilder setChartSettings(ChartSettings chartSettings) {
    this.chartSettings = chartSettings;
    return this;
  }

  @Override
  public ChartSlugBuilder setChartAttributes(String chartSettings) {
    this.chartAttributes = parseJSON(chartSettings, JsonNode.class);
    return this;
  }

  @Override
  public ChartSlugBuilderImpl addSource(String name, String query) {
    this.sources.add(ChartSource.builder()
        .queryName(name)
        .query(query)
        .build());
    return this;
  }

  @Override
  public ChartSlugBuilderImpl addSource(String name, String query, boolean disabled) {
    this.sources.add(ChartSource.builder()
        .queryName(name)
        .query(query)
        .disabled(disabled)
        .build());
    return this;
  }

  @Override
  public ChartSlugBuilderImpl addSource(String name, String query, boolean disabled, String queryType) {
        this.sources.add(ChartSource.builder()
                .queryName(name)
                .query(query)
                .disabled(disabled)
                .queryType(queryType)
                .build());
        return this;
    }

    @Override
    public ChartSlugBuilder addSource(String name, String query, boolean disabled, String queryBuilderSerialization,
                                      boolean queryBuilderEnabled) {
      this.sources.add(ChartSource.builder()
                .queryName(name)
                .query(query)
                .disabled(disabled)
                .queryBuilderSerialization(queryBuilderSerialization)
                .queryBuilderEnabled(queryBuilderEnabled)
                .build());
        return this;
    }

  @Override
  public ChartSlugBuilderImpl addSource(String name, String query, boolean disabled, String queryBuilderSerialization,
                                        boolean queryBuilderEnabled, String queryType) {
    this.sources.add(ChartSource.builder()
            .queryName(name)
            .query(query)
            .disabled(disabled)
            .queryBuilderSerialization(queryBuilderSerialization)
            .queryBuilderEnabled(queryBuilderEnabled)
            .queryType(queryType)
            .build());
    return this;
  }

  @Override
  public ChartSlugBuilderImpl addFocusedHost(String hostName) {
    this.focusedHosts.add(hostName);
    return this;
  }

  @Override
  public String build() {
    return slugVersion.getVersionStr() + internalBuild();
  }

  @Override
  public String buildAndEscape() {
    String slug = build();
    return UrlEscapers.urlFragmentEscaper().escape(slug);
  }

  /**
   * This only works in v1 slugs, as it encodes special characters as well. So the result it
   * generated will only be v1 slugs.
   */
  @Override
  @Deprecated
  public String buildAndEncode() {
    String slug = SlugVersion.V1.getVersionStr() + internalBuild();
    return URLEncoder.encode(slug, StandardCharsets.UTF_8);
  }

  @VisibleForTesting
  String internalBuild() {
    Preconditions.checkState(!Strings.isNullOrEmpty(customerId), "customerId cannot be empty or null");
    Preconditions.checkState(start != null, "start must be set");
    Preconditions.checkState(end != null, "end must be set");
    Preconditions.checkArgument(base >= 1, "base must be >= 1");
    Preconditions.checkState(sources.size() > 0, "must have at least one chart source");

    try {
      return mapper.writeValueAsString(toChartSlug());
    } catch (JsonProcessingException e) {
      throw Throwables.propagate(e);
    }
  }

  /**
   * mapper converts current instance to a {@link ChartSlug}.
   *
   * @return {@link ChartSlug} which used in serialization.
   */
  private ChartSlug toChartSlug() {
    return ChartSlug.builder()
        .customerId(this.customerId)
        .chart(Chart.builder()
            .id(this.id)
            .name(this.name)
            .units(this.units)
            .base(this.base)
            .description(this.chartDescription)
            .chartSources(this.sources)
            .chartSettings(this.chartSettings)
            .chartAttributes(this.chartAttributes)
            .build())
        .timeRange(TimeRange.builder()
            .startTime((long) Math.floor(this.start / 1000.0))
            .duration((long) Math.floor((this.end - this.start) / 1000.0))
            .granularity(this.granularity)
            .compare(this.compare)
            .build())
        .focusedHosts(this.focusedHosts)
        .build();
  }

  /**
   * Parse nullable string into its intended structure.
   */
  @Nullable
  private <T> T parseJSON(@Nullable String JSONString, Class<T> tClazz) {
    if (Strings.isNullOrEmpty(JSONString)) {
      return null;
    }
    try {
      return JSONMapper.readValue(JSONString, tClazz);
    } catch (JsonProcessingException ex) {
      Throwables.propagate(ex);
      return null;
    }
  }
}
