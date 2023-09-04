package util_parser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Objects;

public class JsonParsing implements AutoCloseable {

  /*
   *This class is for creating the parser and
   * the objectMapper will also auto closed after finishing
   * consuming the Data
   */

  protected JsonParser jsonParser;

  public JsonParsing( InputStream inputStream ) {init( inputStream );}

  private void init( InputStream inputStream ) {
    Objects.requireNonNull( inputStream );
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );
      objectMapper.configure( JsonGenerator.Feature.AUTO_CLOSE_TARGET, true );
      jsonParser = objectMapper.getFactory().createParser( inputStream );
    }
    catch ( IOException e ) {
      throw new UncheckedIOException( e );
    }
  }

  @Override public void close() throws Exception {
    try {
      jsonParser.close();
    }
    catch ( IOException e ) {
      throw new UncheckedIOException( e );
    }
  }
}
