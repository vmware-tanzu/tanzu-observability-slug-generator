/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.dashboard;

import com.wavefront.slug.SlugVersion;

import lombok.experimental.UtilityClass;

/**
 * Static factories for Dashboard slug builder ({@link DashboardSlugBuilder}).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@UtilityClass
public final class DashboardSlugBuilders {
  public static DashboardSlugBuilder slugBuilder() {
    return new DashboardSlugBuilderImpl(SlugVersion.V2);
  }

  @Deprecated
  public static DashboardSlugBuilder slugBuilderV1() {
    return new DashboardSlugBuilderImpl(SlugVersion.V1);
  }
}
