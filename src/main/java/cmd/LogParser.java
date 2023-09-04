package cmd;

import model.TimeLogEntry;
import util_parser.JsonList;

import java.io.InputStream;

public class LogParser extends JsonList<TimeLogEntry> {
  public LogParser( InputStream inputStream ) {
    super( inputStream, TimeLogEntry.class );
  }
}
