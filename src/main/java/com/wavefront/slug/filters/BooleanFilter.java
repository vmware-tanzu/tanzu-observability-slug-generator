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
