package org.hiatusuk.redis.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.hiatusuk.redis.demo.Content;
import org.hiatusuk.redis.demo.Knowledge;
import org.hiatusuk.redis.demo.PageContent;

import java.time.LocalDateTime;
import java.util.Collections;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class RedisSerializationTest {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void configureObjectMapper() {
        MAPPER.findAndRegisterModules();
        // Just to be explicit...
        MAPPER.registerModules( new ParanamerModule(), new ParameterNamesModule());
    }

    @Test
    public void testGenericSerDeser() {
        // Only Mapper required up-front. Destination type is needed for each deserialization
        final GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer( MAPPER );

        final PageContent original = testContent();
        final PageContent dest = serializer.deserialize(serialize(original, serializer), PageContent.class);

        assertThat(dest).isEqualTo(original);
    }

    @Test
    public void testExplicitlyTypedSerDeser() {
        // Must bind deser destination type and configure Mapper up-front. No additional config needed.
        final Jackson2JsonRedisSerializer<PageContent> serializer = new Jackson2JsonRedisSerializer<>( PageContent.class );
        serializer.setObjectMapper(MAPPER);

        final PageContent original = testContent();
        final PageContent dest = serializer.deserialize(serialize(original, serializer));

        assertThat(dest).isEqualTo(original);
    }

    // Helpers
    private static PageContent testContent() {
        return new PageContent("blah", new Knowledge(), Collections.singletonList( new Content( LocalDateTime.now() ) ));
    }

    private static byte[] serialize(PageContent content, RedisSerializer serializer) {
        final byte[] serdBytes = serializer.serialize(content);
        System.out.println("Serialized as: " + new String(serdBytes, UTF_8));
        return serdBytes;
    }
}
