package org.hiatusuk.redis.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;

import java.util.List;
import java.util.Objects;

public class PageContent {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final Knowledge knowledge;

    @JsonProperty
    private final List<Content> content;

    @JsonCreator
    public PageContent(final String id, final Knowledge knowledge, final List<Content> content) {
        super();
        this.id = id;
        this.knowledge = knowledge;
        this.content = content;
    }

    @VisibleForTesting
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PageContent other = (PageContent) o;
        return Objects.equals(id, other.id) &&
                Objects.equals(knowledge, other.knowledge) &&
                Objects.equals(content, other.content);
    }
}
