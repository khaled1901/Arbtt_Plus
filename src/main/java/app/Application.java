package app;

import GUI.InputGUI;
import cmd.Dump;
import model.TimeLogEntry;
import cmd.LogParser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static cmd.Dump.Format.JSON;
import static java.time.LocalDate.now;

public class Application {
  public static void main( String[] args ) {
    try {
      new InputGUI();
    }
    catch ( Exception e ) {
      throw new RuntimeException(e);
    }
  }
  public static List<TimeLogEntry> getArbttLog() {
    List<TimeLogEntry> timeLogEntries = new ArrayList<>( 2048 );
    new Dump().format( JSON ).run( inputStream -> {
      // We can read from the external process's output stream here.
      new LogParser( inputStream ).forEach( timeLogEntries::add );
    } );
    return timeLogEntries;
  }

  public static void writeHTML( String pageName, LogServices entries, int days ) {
    try ( HTMLPage page = new HTMLPage( pageName ) ) {
      page.writeHeader();
      SortedMap<LocalDate, List<TimeLogEntry>> entriesByDay = entries.entriesSortedByDate();
      entriesByDay.forEach( ( date, entry ) -> {
        if ( !date.isBefore( now().minusDays( days ) ) ) {
          writeChartsPerDay( page, date, entry );
        }
      } );
    }
  }

  private static void writeChartsPerDay( HTMLPage page, LocalDate date, List<TimeLogEntry> dateTimeProgram ) {
    var titlesPerDay = LogServices.totalTitleTimeProProgramPerDay( dateTimeProgram );
    Map<String, Integer> programsPerDay = new HashMap<>();
    String day = "%d%s".formatted( date.getDayOfMonth(), date.getMonth() );

    titlesPerDay.forEach( (title, time) -> {
      String program = title.substring( 0, title.indexOf( ',' ) );
      if ( programsPerDay.containsKey( program ) ){
        int value = programsPerDay.get( program );
        programsPerDay.put( program, (value+time)  );
      }else {
        programsPerDay.put( program, time );
      }
    } );
    page.writeFormatted( """
                                         <script type="text/javascript">
                                             google.charts.load('current', { 'packages': ['corechart', 'table'] });
                                             google.charts.setOnLoadCallback(draw%s);
                                             function draw%s() {
                                                 var pieData = new google.visualization.DataTable();
                                                 pieData.addColumn('string', 'Program');
                                                 pieData.addColumn('number', 'Minuten');
                                                 pieData.addColumn({type: 'string' ,role: 'tooltip'})
                                                 pieData.addRows([
                                                          
                             """, day, day );
    programsPerDay.forEach(
        ( program, time ) -> page.writeFormatted( "                       ['%s', %d, '%s'],\n", program, time,
                                                                             program+", "+(time/60)+" Stunden und "+(time%60)+" Minuten" ) );

    page.writeFormatted( """
                                                 ]);
                                                          
                                                 var pieOptions = {
                                                     title: '%s',
                                                     titleTextStyle: { color: 'black', fontSize: 30, bold: true },
                                                     is3D: false,
                                                     pieSliceText: 'percentage',
                                                     sliceVisibilityThreshold: .00,
                                                     tooltip: { trigger: 'focus' },
                                                     legend: { position: 'labeled' }
                                                 };
                                                          
                                                 var tableData = new google.visualization.DataTable();
                                                 tableData.addColumn('string', 'Program');
                                                 tableData.addColumn('string', 'Title');
                                                 tableData.addColumn('string', 'Dauer');
                                                 tableData.addRows([
                             """, date.toString() );
    titlesPerDay.forEach( ( title, time ) -> {
      String duration = "%d Stunden, %d Minuten".formatted( (time / 60), (time % 60) );
      page.writeFormatted( "                       ['%s', '%s', '%s'],\n",
                           title.substring( 0, title.indexOf( ',' ) ),
                           title.substring( title.indexOf( ',' ) + 1 ), duration );
    } );
    page.writeFormatted( """
                                                 ]);
                                                 var tableOptions = {
                                                     sort: 'enable',
                                                     sortColumn: 0,
                                                     width: '100%%',
                                                     height: '100%%'
                                                 };
                                                          
                                                 var chart = new google.visualization.PieChart(document.getElementById('%s-pie'));
                                                 chart.draw(pieData, pieOptions);
                                                 var table = new google.visualization.Table(document.getElementById('%s-table'));
                                                 table.draw(tableData, tableOptions);
                                             }
                                         </script>
                                         <div class="row">
                                             <div style="width: 100%%;">
                                                 <div id="%s-pie" style="width: 1200px; height: 400px;"></div>
                                                 <div id="%s-table" style="width: 1200px; height: 400px;"></div>
                                             </div>
                                         </div>
                             """, date.toString(), date.toString(), date.toString(),
                         date.toString() );
  }
}