package week3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class TestFileNIO {
	
	public static void main(String[] args, Object message) throws IOException {
		// reading from a file
		// get the channel from the FileInputStream
		FileInputStream fin = new FileInputStream( "readandshow.txt");
		FileChannel fcRx = fin.getChannel();
		
		// create a buffer
		ByteBuffer bufferRx = ByteBuffer.allocate( 1024 );
		
		// read from the channel into the buffer
		fcRx.read( bufferRx );
		
		// writing to a file
		// start by getting a channel from a FileOutputStream;
		FileOutputStream fout = new FileOutputStream("writesomebytes.txt");
		FileChannel fcTx = fout.getChannel();
		
		// create a buffer and put some data in it
		ByteBuffer bufferTx = ByteBuffer.allocate(1024);
//		for (int i = 0; i < message.length; i++ ) {
//			bufferTx.put(message[i]);
//		}
		bufferTx.flip();
		
		// write to buffer
		fcTx.write(bufferTx);
		
		// the buffer's internal accounting system keeps track of how much data it contains
		// and how much is left to be written
		
	}
}