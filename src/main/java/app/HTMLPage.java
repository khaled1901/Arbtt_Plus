package app;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;


public class HTMLPage implements AutoCloseable {
  private final PrintWriter output;
  public HTMLPage( String pageName ) {
    try {
      output = new PrintWriter( pageName );
    }
    catch ( FileNotFoundException e ) {
      throw new UncheckedIOException( e );
    }
  }
  public void writeFormatted( String s, Object... args ) {
    output.printf( s, args );
  }
  public void writeHeader() {
    output.println( """
                        <!DOCTYPE html>
                        <html>

                        <head>
                            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
                            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                        </head>

                        <body>
                            <div class="container">
                                <div style="width: 200%;">
                                    <h1>Nutzungszeiten Arbtt-Plus</h1>
                                                  """ );
  }
  public void writeFooter() {
    output.println( """
                                </div>
                            </div>
                        </body>
                        </html>
                        """ );}
  @Override public void close() {
    writeFooter();
    output.close();
  }
}

 /*
  *
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
    <div class="container">
        <div style="width: 200%;">
            <h1>Nutzungszeiten Arbtt-Plus</h1>

            <script type="text/javascript">
                google.charts.load('current', { 'packages': ['corechart', 'table'] });
                google.charts.setOnLoadCallback(drawdate);
                function drawdate() {
                    var pieData = new google.visualization.DataTable();
                    pieData.addColumn('string', 'Program');
                    pieData.addColumn('number', 'Minuten');
                    pieData.addColumn({type: 'string' ,role: 'tooltip'})
                    pieData.addRows([
                    ]);

                    var pieOptions = {
                        title: 'date',
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
                    ]);
                    var tableOptions = {
                        sort: 'enable',
                        sortColumn: 0,
                        width: '100%',
                        height: '100%'
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('date-pie'));
                    chart.draw(pieData, pieOptions);
                    var table = new google.visualization.Table(document.getElementById('date-table'));
                    table.draw(tableData, tableOptions);
                }
            </script>
            <div class="row">
                <div style="width: 100%;">
                    <div id="date-pie" style="width: 1200px; height: 400px;"></div>
                    <div id="date-table" style="width: 1200px; height: 400px;"></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
*/