package week3;
//http://www.deepakgaikwad.net/index.php/2013/08/26/java-nio-tutorial.html

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
public class NIOSimpleFileWriter {
 
    /**
     * @param args
     */
    public static void main(String[] args) {

        Path file = null;
        BufferedWriter bufferedWriter  = null;
        try{
            file = Files.createFile(Paths.get("SimpleNIOData.txt"));
 
            Charset charset = Charset.forName("UTF-8");
 
            String line = "I am writing using NIO.";
 
            bufferedWriter = Files.newBufferedWriter(file, charset);
            bufferedWriter.write(line, 0, line.length());
 
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufferedWriter.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
    }