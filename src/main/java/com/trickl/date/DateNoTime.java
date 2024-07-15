package com.trickl.date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trickl.json.JsonDateNoTimeDeserializer;
import com.trickl.json.JsonDateNoTimeSerializer;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Value;

/** A date without a time component. */
@JsonDeserialize(using = JsonDateNoTimeDeserializer.class)
@JsonSerialize(using = JsonDateNoTimeSerializer.class)
@Value
@EqualsAndHashCode
public final class DateNoTime {

  private final Date date;

  @Override
  public String toString() {
    return DateNoTimeFormat.format(this.getDate());
  }
}
