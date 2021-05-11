/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.chart;

import com.wavefront.slug.SlugVersion;

import lombok.experimental.UtilityClass;

/**
 * Static factories for Chart slug builder ({@link ChartSlugBuilder}).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@UtilityClass
public final class ChartSlugBuilders {
  public static ChartSlugBuilder slugBuilder() {
    return new ChartSlugBuilderImpl(SlugVersion.V2);
  }

  @Deprecated
  public static ChartSlugBuilder slugBuilderV1() {
    return new ChartSlugBuilderImpl(SlugVersion.V1);
  }
}
