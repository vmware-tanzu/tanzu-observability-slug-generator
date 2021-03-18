/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.chart;

/**
 * Static factories for Chart slug builder ({@link ChartSlugBuilder}).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public final class ChartSlugBuilders {
  private ChartSlugBuilders() {
    throw new UnsupportedOperationException("ChartSlugBuilders is an static factory class, cannot be" +
        " instantiated.");
  }

  public static ChartSlugBuilder slugBuilder() {
    return new ChartSlugBuilderImpl();
  }
}
