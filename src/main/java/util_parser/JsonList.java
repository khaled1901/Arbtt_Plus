package util_parser;

import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class JsonList<T> extends JsonParsing implements Iterable<T> {
  /*
  * This abstract class has the function of defining and isolating the JSON Objects
    to convert them into Java Objects based on Log (see Class: Log)
  *
  */
  private final Class<T> type;

  /**
   * Constructor for ListElementJsonParser.
   *
   * @param inputStream of the source JSON stream.
   * @param type        element type of the array elements.
   */

  protected JsonList( InputStream inputStream, Class<T> type ) {
    super( inputStream );
    this.type = Objects.requireNonNull( type );
    advanceCursorBehindArray();
  }

  /**
   * Calls nextToken() on the internal JsonParser as long as until
   * inside the first array of this JSON document. For example:
   * The method will skip this header:
   * <pre>
   *   {
   *   "root" : [
   * </pre>
   * Important: the cursor is on the first element in this array (or end of array).
   */
  protected void advanceCursorBehindArray() {
    try {
      // Consume every token until start of array.
      while ( jsonParser.nextToken() != JsonToken.START_ARRAY ) {
        // Intentionally empty
      }

      // Advance cursor behind start of array on the first element.
      jsonParser.nextToken();
    }
    catch ( IOException e ) {
      throw new UncheckedIOException( e );
    }
  }

  @Override public Iterator<T> iterator() {
    return new Iterator<>() {
      @Override public boolean hasNext() {
        // testing if we are at the start of an object
        return jsonParser.currentToken() == JsonToken.START_OBJECT;
      }

      @Override public T next() {
        if ( !hasNext() ) {
          throw new NoSuchElementException();
        }
        try {
          // here I am getting the types of my objects from the parser
          var classType = jsonParser.readValueAs( type );
          jsonParser.nextToken();
          return classType;
        }
        catch ( IOException e ) {
          throw new IllegalStateException( e );
        }
      }
    };
  }
}
