package week3;

//http://www.deepakgaikwad.net/index.php/2013/08/26/java-nio-tutorial.html

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOAsynchronousFileWriterExample {
	
	public static void main(String[] args) {
		
		Path file = null;
        AsynchronousFileChannel asynchFileChannel = null;
        String filePath= "NIOAsyncDataFile.txt";
        try {
            file = Paths.get(filePath);
            asynchFileChannel = AsynchronousFileChannel.open(file,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE);
 
            CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    System.out.println("Thread: "+ Thread.currentThread().getName()+" File Write Completed with Result:"
                            + result);
                }
 
                @Override
                public void failed(Throwable e, Object attachment) {
                    System.err.println("File Write Failed Exception:");
                    e.printStackTrace();
                }
            };
            System.out.println("Thread: "+Thread.currentThread().getName()+" Before write call");
            asynchFileChannel.write(ByteBuffer
                    .wrap("I am writing using Asynchronous NIO.".getBytes()),
                    0, "File Write", handler);
            System.out.println("Thread: "+Thread.currentThread().getName()+" After write call");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                asynchFileChannel.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
		
//		Path file = null;
//		AsynchronousFileChannel asynchFileChannel = null;
//		String filePath = "NIOAsyncDataFile.txt";
//		
//		try {
//			file = Paths.get(filePath);
//			asynchFileChannel = AsynchronousFileChannel.open(file, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//			
//			CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {
//
//				@Override
//				public void completed(Integer result, Object attachment) {
//					System.out.println("Thread: " + Thread.currentThread().getName() + " File Write Completed with Result: " + result);
//					
//				}
//
////				@Override
////				public void failed(Throwable e, Object attachment) {
////					System.err.println("File Write Failed Exception:");
////					e.printStackTrace();
////					
////				}
//				
//				@Override
//                public void failed(Throwable e, Object attachment) {
//                    System.err.println("File Write Failed Exception:");
//                    e.printStackTrace();
//                }
//            };
//			}
//		}
	}
	
}