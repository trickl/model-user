package com.trickl.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.trickl.date.DateNoTime;
import com.trickl.date.DateNoTimeFormat;
import java.io.IOException;

/** Serialize a DateNoTime object to a JSON string. */
public class JsonDateNoTimeSerializer extends StdSerializer<DateNoTime> {

  public JsonDateNoTimeSerializer() {
    this(null);
  }

  public JsonDateNoTimeSerializer(Class<DateNoTime> t) {
    super(t);
  }

  @Override
  public void serialize(DateNoTime value, JsonGenerator gen, SerializerProvider arg2)
      throws IOException, JsonProcessingException {
    if (value.getDate() != null) {
      gen.writeString(DateNoTimeFormat.format(value.getDate()));
    }
  }
}
