package week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class UniqueWordsTyped {
	public static void main(String[] args) {
		
		// Usage check and open file
		if ( args.length != 1 ) {
			System.err.println( "Usage: java UniqueWords word-file " );
			System.exit( 1 );
		}
		
		StreamTokenizer in = null;
		try {
			in = new StreamTokenizer( new BufferedReader( new FileReader( args[0])));
			in.ordinaryChar( '.' );
		}
		catch ( FileNotFoundException ex ) {
			System.err.println( "UniqueWords:  " + ex.getMessage());
			System.exit(1);
		}
		
		try {
			Set set = new HashSet();
			
			while (( in.nextToken() != in.TT_EOF )) {
				if ( in.ttype == in.TT_WORD ) 
				{
					set.add( in.sval );
				}
			}
			System.out.println( "There are " + set.size() + " unique words");
			System.out.println( set );	

		}
		catch ( IOException ex ) {
			System.err.println( "UniqueWords; " + ex.getMessage());
			System.exit( 1 );

		}
	
		
		try {
			SortedSet set = new TreeSet();	// option 1 & option 3
//			Set set = new HashSet();		// option 2
			
			while (( in.nextToken() != in.TT_EOF )) {
				if ( in.ttype == in.TT_WORD )
				{
					set.add(in.sval);
				}
			}
			
			System.out.println( "There are " + set.size() + " unique words");
//			System.out.println( set );					// option 1
//			System.out.println( new TreeSet(set));		// option 2
			
			Iterator elements = set.iterator();
		}
		catch ( IOException ex ) {
			System.err.println( " UniqueWords:  " + ex.getMessage());
			System.exit(1);
		}
		
		
	}
}