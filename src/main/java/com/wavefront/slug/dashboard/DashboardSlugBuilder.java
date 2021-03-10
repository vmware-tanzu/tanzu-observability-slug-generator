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

import org.joda.time.ReadableInstant;

/**
 * Interface of {@link DashboardSlugBuilder}
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public interface DashboardSlugBuilder {
  /**
   * Set the start millis.
   *
   * *Notice* If {@link #setLiveRefresh} is not called and liveliness is not set to False, the start
   * time will be changed after accessing using the url, because liveliness will always start from
   * the latest time.
   *
   * @param startMillis Start millis.
   * @return The builder.
   */
  DashboardSlugBuilder setStart(long startMillis);

  /**
   * Set the start instant.
   *
   * *Notice* If {@link #setLiveRefresh} is not called and liveliness is not set to False, the start
   * time will be changed after accessing using the url, because liveliness will always start from
   * the latest time.
   *
   * @param instant The start instant.
   * @return The builder.
   */
  DashboardSlugBuilder setStart(ReadableInstant instant);

  /**
   * Set the end millis.
   *
   * *Notice* If {@link #setLiveRefresh} is not called and liveliness is not set to False, the end
   * time will only be used to calculate the duration, because liveliness will always start from
   * the latest time.
   *
   * @param endMillis End millis.
   * @return The builder.
   */
  DashboardSlugBuilder setEnd(long endMillis);

  /**
   * Set the end millis.
   *
   * *Notice* If {@link #setLiveRefresh} is not called and liveliness is not set to False, the end
   * time will only be used to calculate the duration, because liveliness will always start from
   * the latest time.
   *
   * @param instant The end instant.
   * @return The builder.
   */
  DashboardSlugBuilder setEnd(ReadableInstant instant);

  /**
   * Set the flag whether we want to turn on live refresh (default is off, so used it when you want
   * to turn on live refresh).
   *
   * @param enableLiveRefresh Whether we want to turn on live refresh
   * @return The builder.
   */
  DashboardSlugBuilder setLiveRefresh(boolean enableLiveRefresh);

  /**
   * Set the live refresh window size, this value needs to be provided if live refresh is turned on
   * or default is `2h`.
   *
   * @param windowSize The window size.
   * @return The builder.
   */
  DashboardSlugBuilder setWindowSize(String windowSize);

  /**
   * Sets the comparison option for the entire dashboard. Valid values are determined by the FE.
   * Possible values include: "1d", "1w", "1m". Defaults to null.
   *
   * @param compare Comparison option to set for the dashboard.
   * @return The builder.
   */
  DashboardSlugBuilder setCompare(String compare);

  /**
   * Sets the value of a simple dashboard variable in the dashboard.
   * <p>
   * The name is the name that you defined when you create a dashboard variable, not the display name
   * of the dashboard variable. You can get the name of the variable by hanging your cursor on your
   * dashboard variable display name.
   * <p>
   * This is not a place to define a new dashboard variable, instead, it is a place to assign a value
   * to the existing variable.
   *
   * @param name                     Name of the dashboard variable (Not label)
   * @param value                    the value of the variable.
   * @return The builder.
   */
  DashboardSlugBuilder setSimpleDashboardVariable(String name, String value);

  /**
   * Sets the value of a list dashboard variable in the dashboard.
   * <p>
   * The name is the name that you defined when you create a dashboard variable, not the display name
   * of the dashboard variable. You can get the name of the variable by hanging your cursor on your
   * dashboard variable display name.
   * <p>
   * This is not a place to define a new dashboard variable, instead, it is a place to assign a value
   * to the existing variable.
   *
   * @param name                      Name of the dashboard variable (Not label).
   * @param selected                  the selected value from the list of values.
   * @return The builder.
   */
  DashboardSlugBuilder setListDashboardVariable(String name, String selected);

  /**
   * Sets the value of a list dashboard variable in the dashboard.
   * <p>
   * The name is the name that you defined when you create a dashboard variable, not the display name
   * of the dashboard variable. You can get the name of the variable by hanging your cursor on your
   * dashboard variable display name.
   *
   * @param name                      Name of the dashboard variable (Not label).
   * @return The builder.
   */
  DashboardSlugBuilder setDynamicDashboardVariable(String name);

  /**
   * Build the dashboard slug.
   *
   * @return The dashboard slug.
   */
  String build();

  /**
   * Build and escape the dashboard slug to be used in the dashboard url.
   */
  String buildAndEscape();
}
