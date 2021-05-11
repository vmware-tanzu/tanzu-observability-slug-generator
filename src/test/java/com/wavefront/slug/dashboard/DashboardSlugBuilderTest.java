/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.dashboard;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link DashboardSlugBuilderImpl}.
 * <p>
 * Mainly test for RISON Serialization of {@link DashboardSlug}
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public class DashboardSlugBuilderTest {
  private DashboardSlugBuilder builder;

  @BeforeEach
  public void setup() {
    this.builder = DashboardSlugBuilders.slugBuilder();
  }

  @Test
  @Tag("Build")
  @DisplayName("With only start time and end time")
  public void testBuild() throws Exception {
    String slug = builder
        .setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC))
        .setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC))
        .build();

    // verify
    String expectedSlug = "_v02(g:(d:7200,s:1373948820))";
    String message = String.format("The expected URL slug should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug).as(message).isEqualTo(expectedSlug);
  }

  @Test
  @Tag("Build")
  @DisplayName("With simple dashboard variable")
  public void testBuildWithSimpleVariable() throws Exception {
    String slug = builder
        .setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC))
        .setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC))
        .setSimpleDashboardVariable("test", "Test")
        .build();

    // verify
    String expectedSlug = "_v02(g:(d:7200,s:1373948820),p:(test:(v:Test)))";
    String message = String.format("The expected URL slug should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug).as(message).isEqualTo(expectedSlug);
  }

  @Test
  @Tag("Build")
  @DisplayName("With list dashboard variable")
  public void testBuildWithListVariable() throws Exception {
    String slug = builder
        .setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC))
        .setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC))
        .setListDashboardVariable("test", "Test")
        .build();

    // verify
    String expectedSlug = "_v02(g:(d:7200,s:1373948820),p:(test:(s:Test)))";
    String message = String.format("The expected URL slug should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug).as(message).isEqualTo(expectedSlug);
  }

  @Test
  @Tag("Build")
  @DisplayName("With dynamic dashboard variable")
  public void testBuildWithDynamicVariable() throws Exception {
    String slug = builder
        .setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC))
        .setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC))
        .setDynamicDashboardVariable("test", null)
        .build();

    // verify
    String expectedSlug = "_v02(g:(d:7200,s:1373948820),p:(test:()))";
    String message = String.format("The expected URL slug should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug).as(message).isEqualTo(expectedSlug);
  }

  @Test
  @Tag("Build")
  @DisplayName("With dynamic dashboard variable")
  public void testBuildWithDynamicVariableWithValue() throws Exception {
    String slug = builder
        .setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC))
        .setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC))
        .setDynamicDashboardVariable("test", "Test")
        .build();

    // verify
    String expectedSlug = "_v02(g:(d:7200,s:1373948820),p:(test:(v:Test)))";
    String message = String.format("The expected URL slug should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug).as(message).isEqualTo(expectedSlug);
  }
}
