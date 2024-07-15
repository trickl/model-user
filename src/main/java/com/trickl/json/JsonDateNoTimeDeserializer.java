package com.trickl.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.trickl.date.DateNoTime;
import com.trickl.date.DateNoTimeFormat;
import java.io.IOException;
import java.text.ParseException;

/** Deserialize a DateNoTime object from a JSON string. */
public class JsonDateNoTimeDeserializer extends StdDeserializer<DateNoTime> {

  public JsonDateNoTimeDeserializer() {
    this(null);
  }

  public JsonDateNoTimeDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public DateNoTime deserialize(JsonParser jp, DeserializationContext dc)
      throws IOException, JsonProcessingException {
    String value = jp.getText();
    if (value == null) {
      return null;
    }
    try {
      return new DateNoTime(DateNoTimeFormat.parse(value));
    } catch (ParseException ex) {
      throw new JsonParseException(jp, "Could not parse DateNoTime:" + value);
    }
  }
}
