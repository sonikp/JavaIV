package week3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ObjectSer {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		FileOutputStream out = new FileOutputStream("theTime");
		ObjectOutputStream s = new ObjectOutputStream(out);
		s.writeObject("Today");
		s.writeObject(new Date());
		s.flush();
		
		FileInputStream in = new FileInputStream("theTime");
		ObjectInputStream s2 = new ObjectInputStream(in);
		String today = (String)s2.readObject();
		Date date = (Date)s2.readObject();
		
		System.out.println(today + " " + date);
		
		
	}
}