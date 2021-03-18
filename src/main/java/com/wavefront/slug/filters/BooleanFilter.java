/*
Tanzu Observability Slug Generator
Copyright 2020 VMware, Inc.

This product is licensed to you under the Apache 2.0 license (the "License"). You may not use this
product except in compliance with the Apache 2.0 License.

This product may include a number of subcomponents with separate copyright notices and license terms.
Your use of these subcomponents is subject to the terms and conditions of the subcomponent's license,
as noted in the LICENSE file.
*/

package com.wavefront.slug.filters;

import java.util.Objects;

/**
 * filter which filters out false boolean field, used to omit not-disabled field by default.
 */
public class BooleanFilter {
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return true;
      }
      return Objects.equals(Boolean.FALSE, obj);
    }
}
