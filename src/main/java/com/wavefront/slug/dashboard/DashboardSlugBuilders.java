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

/**
 * Static factories for Dashboard slug builder ({@link DashboardSlugBuilder}).
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public final class DashboardSlugBuilders {
  private DashboardSlugBuilders() {
    throw new UnsupportedOperationException("DashboardSlugBuilders is an static factory class, cannot be" +
        " instantiated.");
  }

  public static DashboardSlugBuilder slugBuilder() {
    return new DashboardSlugBuilderImpl();
  }
}
