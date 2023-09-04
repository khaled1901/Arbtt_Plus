package model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;

public class DateAndTime extends JsonDeserializer<ZonedDateTime> {

  @Override public ZonedDateTime deserialize( JsonParser p, DeserializationContext d )
      throws IOException {
    return ZonedDateTime.parse( p.getText() );
  }
}
