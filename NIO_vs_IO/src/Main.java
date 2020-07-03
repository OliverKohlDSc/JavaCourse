import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("./customers.txt", "rw");
		FileChannel inputChannel = file.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(32);
		
		int bytesRead = inputChannel.read(buffer);
		
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			
			buffer.flip();
			
			while (buffer.hasRemaining()) {
				System.out.print((char)buffer.get());
			}
			
			buffer.clear();
			bytesRead = inputChannel.read(buffer);
		}
		
		file.close();
		
		
		FileInputStream input = null;
		FileOutputStream output = null;
		
		try {
			input = new FileInputStream("./customers.txt");
			output = new FileOutputStream("./output.txt");
			
			int i;
			while (( i = input.read()) != -1) {
				output.write(i);
			}
		}
		finally {
			if (input != null) {
				input.close();
			}
			
			if (output != null) {
				output.close();
			}
		}
	}

}
