package org.hiatusuk.redis.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;

import java.time.LocalDateTime;
import java.util.Objects;

public class Content {
    @JsonProperty
    private final LocalDateTime time;

    @JsonCreator
    public Content(final LocalDateTime time) {
        this.time = time;
    }

    @VisibleForTesting
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Content other = (Content) o;
        return Objects.equals(time, other.time);
    }
}
