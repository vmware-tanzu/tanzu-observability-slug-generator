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
