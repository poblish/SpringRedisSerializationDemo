package org.hiatusuk.redis.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;

import java.math.BigDecimal;
import java.util.Objects;

public class Knowledge {
    @JsonProperty
    private final String data = "meta";

    @JsonProperty
    private final BigDecimal val = BigDecimal.valueOf(1222);

    @VisibleForTesting
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Knowledge other = (Knowledge) o;
        return Objects.equals(data, other.data) &&
                Objects.equals(val, other.val);
    }
}
