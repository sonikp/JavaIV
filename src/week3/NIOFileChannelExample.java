package week3;

//http://www.deepakgaikwad.net/index.php/2013/08/26/java-nio-tutorial.html

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannelExample {

	public static void main(String[] args) {

		RandomAccessFile file = null;

		try {
			file = new RandomAccessFile("NIODataFile.txt", "r");

			FileChannel fileChannel = file.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(24);
			int bytes = fileChannel.read(buffer);

			while (bytes != -1) {
				buffer.flip();

				while (buffer.hasRemaining()) {
					System.out.print((char)buffer.get());
				}

				buffer.clear();
				bytes = fileChannel.read(buffer);

			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				file.close();
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}
}
