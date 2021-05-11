/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wavefront.slug;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enums represents all the supported slug versions.
 */
@Getter
@AllArgsConstructor
public enum SlugVersion {
  @Deprecated
  V1("_v01"),
  V2("_v02");

  private final String versionStr;
}
