/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug.dashboard;

import lombok.experimental.UtilityClass;

/**
 * Static factories for Dashboard slug builder ({@link DashboardSlugBuilder}).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
@UtilityClass
public final class DashboardSlugBuilders {
  public static DashboardSlugBuilder slugBuilder() {
    return new DashboardSlugBuilderImpl();
  }
}
