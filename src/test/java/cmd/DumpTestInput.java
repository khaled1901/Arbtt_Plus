package cmd;

import app.LogServices;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static app.Application.getArbttLog;
import static app.Application.writeHTML;

public class DumpTestInput {
  @Test
  void run(){
    var logService = new LogServices( getArbttLog() );
    writeHTML( LocalDate.now() + "test.html", logService, 60 );
  }
}

