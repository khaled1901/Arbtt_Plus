package cmd;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static cmd.Dump.Format.JSON;

public class DumpTestOutput {
  @Test
  void run() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    new Dump().format( JSON ).run( output );
    System.out.println( output );
  }
}
