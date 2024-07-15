package com.trickl.model.user;

import static com.trickl.assertj.core.api.JsonObjectAssertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.trickl.date.DateNoTime;
import java.util.Date;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class JsonSerializationTest {

  private static ObjectMapper objectMapper;

  @BeforeAll
  static void setup() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @ParameterizedTest
  @MethodSource("pojoProvider")
  void testSerialization(Object obj) throws Exception {
    assertThat(obj)
        .usingObjectMapper(objectMapper)
        .serializesAsExpected()
        .deserializesAsExpected()
        .disallowAdditionalProperties()
        .excludeInlineSchemaPackage("com.trickl.model")
        .schemaAsExpected();
  }

  static Stream<Object> pojoProvider() {
    return Stream.of(
        Subscription.builder()
            .name("Test Subscription")
            .description("A test subscription")
            //.expiryDate(new DateNoTime(new Date()))
            .maxSubscribers(10).build(),
        SubscriptionKey.builder().key("123").build(),
        SubscriptionError.builder().message("Test error").build(),
        Principal.builder().id("123").authority("ROLE_USER").build());
  }
}
