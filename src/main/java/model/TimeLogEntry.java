package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.ZonedDateTime;
import java.util.List;

public class TimeLogEntry {
  @JsonDeserialize( using = DateAndTime.class )
  public ZonedDateTime date;
  public int rate;
  public int inactive;
  public List<WindowsData> windows;
  public String desktop;

  public static class WindowsData {
    public boolean active;
    public boolean hidden;
    public String title;
    public String program;
    public String desktop;

    public String getTitleAndProgram() {
      String finalTitle = title.substring( (title.lastIndexOf( '\\' ) + 1), title.lastIndexOf( '.' ) ).replaceAll( "\\\\[a-z]", "\\ " );
      String finalProgram = program.replaceAll( "'", "" ).replaceAll( "\\\\[a-z]", "\\ " );

      if ( finalProgram.length() > 100 ) {
        return finalTitle + ", " + finalProgram.substring( finalProgram.length() - 50 );
      }
      if ( finalProgram.contains( "-" ) ) {
        return finalTitle + ", " + finalProgram.substring( 0, (finalProgram.lastIndexOf( '-' )) );
      }
      return finalTitle + ", " + finalProgram;
    }

    @Override public String toString() {
      return "WindowInfo{" +
             "active=" + active +
             ", hidden=" + hidden +
             ", title='" + title + '\'' +
             ", program='" + program + '\'' +
             ", desktop='" + desktop + '\'' +
             '}';
    }
  }

  @Override public String toString() {
    return "Log{" +
           "date=" + date +
           ", rate=" + rate +
           ", inactive=" + inactive +
           ", windows=" + windows +
           ", desktop='" + desktop + '\'' +
           '}';
  }
}
// JSON Object looks like this :
   /*
    instance ToJSON (TimeLogEntry CaptureData) where
    toJSON (TimeLogEntry {..}) = object [
        "date" = tlTime,
        "rate" = tlRate,
        "inactive" = cLastActivity tlData,
        "windows" = cWindows tlData, ==>  { [ "active" .= wActive, "hidden" .= wHidden, "title" .= wTitle, "program" .= wProgram, "desktop" .= wDesktop],[...] }
        "desktop" = cDesktop tlData
        ]


{
    "type": "object",
    "title": "Arbtt-Dump JSON-Schema",
    "required": [
        "date",
        "windows",
        "rate",
        "desktop",
        "inactive"
    ],
    "properties": {
        "date": {"type": "string"},
        "windows": {"type": "array", "items": { "type": "object",
                                                "required": [
                                                             "program",
                                                             "active",
                                                             "title"
                                                            ],
                                                 "properties": {
                                                                "program": {"type": "string"},
                                                                "active" : {"type": "boolean"},
                                                                "title"  : {"type": "string"}
                                                                }

                                               }
        },
        "rate": {"type": "integer"},
        "desktop": {"type": "string"},
        "inactive": {"type": "integer"}
    }
}
    */