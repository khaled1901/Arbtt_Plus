package cmd;

import org.buildobjects.process.ProcBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Dump {
  public enum Format {
    JSON
  }

  private String logfile;
  private Format format;
  private int last;

  public Dump format( Format format ) {
    this.format = Objects.requireNonNull( format );
    return this;
  }
  public void run( Consumer<InputStream> consumer ) {
    buildProcBuilderWithArgs().withOutputConsumer( consumer::accept ).run();
  }
  public void run( OutputStream outputStream ) {
    buildProcBuilderWithArgs().withOutputStream( outputStream ).run();
  }
  private ProcBuilder buildProcBuilderWithArgs() {
    List<String> args = new ArrayList<>( 6 );
    args.add( "--format" );
    args.add( format.name() );
    System.out.println( "Executing: arbtt-dump " + String.join( " ", args ) );

    // https://github.com/fleipold/jproc
    return new ProcBuilder( "arbtt-dump" ).withArgs( args.toArray( new String[ args.size() ] ) )
        .withNoTimeout();
  }
}
