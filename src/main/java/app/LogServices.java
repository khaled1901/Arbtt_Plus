package app;

import model.TimeLogEntry;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LogServices {
  private List<TimeLogEntry> timeLogEntries;

  public LogServices( List<TimeLogEntry> timeLogEntries ) {
    this.timeLogEntries = timeLogEntries;
  }

  public static Map<String, Integer> totalTitleTimeProProgramPerDay( List<TimeLogEntry> log ) {
    Map<String, Integer> timeProTitle = new HashMap<>();

    for ( TimeLogEntry logEntry : log ) {
      for ( TimeLogEntry.WindowsData windowsData : logEntry.windows ) {
        if ( timeProTitle.containsKey( windowsData.getTitleAndProgram() ) && windowsData.active ) {
          int value = timeProTitle.get( windowsData.getTitleAndProgram() );
          timeProTitle.put( windowsData.getTitleAndProgram(), value + 1 );
        }
        else if ( windowsData.active ) {
          timeProTitle.put( windowsData.getTitleAndProgram(), 1 );
        }
      }
    }
    return timeProTitle;
  }

  public SortedMap<LocalDate, List<TimeLogEntry>> entriesSortedByDate() {
    return timeLogEntries.stream()
        .collect(
            groupingBySorted( timeTimeLogEntryEntry -> timeTimeLogEntryEntry.date.toLocalDate() ) );
  }

  private static <T, K> Collector<T, ?, SortedMap<K, List<T>>>
  groupingBySorted( Function<? super T, ? extends K> classifier ) {
    return Collectors.groupingBy( classifier, TreeMap::new, Collectors.toList() );
  }
}
