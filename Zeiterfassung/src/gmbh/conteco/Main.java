package gmbh.conteco;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.*;
public class Main {

    private TimeSheet timeSheet = new TimeSheet();

    // Not included: EmployeeGroup
    //               GroupRole
    //               EmployeeRole
    public static void main(String[] args) {
        // 1. Teil -> Befüllen
        // 2. Teil -> Anzeige
        // 3. Teil -> User Input

        Main main = new Main();
        main.displayTimesheet();;
    }

    public void fillTimesheet() throws IOException {
        // NIO.2
        //java.nio.file.Path
        // new java.io.File("").toPath()

        FileInputStream fis = new FileInputStream("");
        ReadableByteChannel source = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("");
        WritableByteChannel destination = fos.getChannel();

        copyData(source, destination);
        source.close();
        destination.close();

        //Pipe p = Pipe.open();
        //Pipe.SourceChannel sc = p.source();
        //Pipe.SinkChannel sc2 = p.sink();


    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);

        while(src.read(buffer) != -1){
            // The buffer is used to drained
            buffer.flip();

            // make sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            //Now the buffer is empty , ready for refilling it again.
            buffer.clear();
        }
    }

    public void displayTimesheet() {
        this.timeSheet.display();
    }
}
