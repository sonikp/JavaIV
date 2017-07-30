package week3;

import java.io.*;
import java.util.*;
public class UniqueWords {
	public static void main( String args[] ) {
		/**
		 * Usage check & open file
		 */
		if ( args.length != 1 ) {
			System.err.println( "Usage: java UniqueWords word-file" );
			System.exit( 1 );
		}
		StreamTokenizer in = null;
		try {
			in = new StreamTokenizer(
					new BufferedReader ( new FileReader( args[ 0 ] ) ) );
			in.ordinaryChar( '.' );
		}
		catch ( FileNotFoundException e ) {
			System.err.println( "UniqueWords: " + e.getMessage() );
			System.exit( 1 );
		}
		
		/**
		 * Counts and displays the unique words?
		 */
//		try {
//			Set set = new HashSet();
//			while ( ( in.nextToken() != in.TT_EOF ) ) {
//				if ( in.ttype == in.TT_WORD )
//					set.add( in.sval );
//			}
//			System.out.println( "There are " + set.size() + " unique words" );
//			System.out.println( set );
//		}
//		catch ( IOException e ) {
//			System.err.println( "UniqueWords: " + e.getMessage() );
//			System.exit( 1 );
//		}
		
		/**
		 * Counts, displays and sorts the unique words?
		 */
//		try {
//			SortedSet set = new TreeSet();
//			while ( ( in.nextToken() != in.TT_EOF ) ) {
//				if ( in.ttype == in.TT_WORD )
//					set.add( in.sval );
//			}
//			System.out.println( "There are " + set.size() + " unique words" );
//			System.out.println( set );
//		}
//		catch ( IOException e ) {
//			System.err.println( "UniqueWords: " + e.getMessage() );
//					System.exit( 1 );
//		}
		
		/**
		 * Or a different way to count, displays and sorts the unique words?
		 */
//		try {
//			Set set = new HashSet();
//			while ( ( in.nextToken() != in.TT_EOF ) ) {
//				if ( in.ttype == in.TT_WORD )
//					set.add( in.sval );
//			}
//			System.out.println( "There are " + set.size() + " unique words" );
//			System.out.println( new TreeSet(set) );
//		}
//		catch ( IOException e ) {
//			System.err.println( "UniqueWords: " + e.getMessage() );
//					System.exit( 1 );
//		}
		
		/**
		 * As pretty output
		 */
//		try {
//			SortedSet set = new TreeSet();
//			while ( ( in.nextToken() != in.TT_EOF ) ) {
//				if ( in.ttype == in.TT_WORD )
//					set.add( in.sval );
//			}
//			System.out.println( "There are " + set.size() + " unique words" );
//			Iterator elements = set.iterator();
//			System.out.println();
//			while ( elements.hasNext() )
//				System.out.println( elements.next() );
//		}
//		catch ( IOException e ) {
//			System.err.println( "UniqueWords: " + e.getMessage() );
//			System.exit( 1 );
//		}
		
		/**
		 * As pretty output
		 */
		try {
			HashMap map = new HashMap();
			Integer one = new Integer( 1 );
			while ( ( in.nextToken() != in.TT_EOF ) ) {
				if ( in.ttype == in.TT_WORD ) {
					Integer freq = ( Integer )map.get( in.sval );
					if ( freq == null )
						freq = one;
					else
						freq = new Integer( freq.intValue() + 1 );
					map.put( in.sval, freq );
				}
			}
			System.out.println( "There are " + map.size() + " unique words" );
			SortedMap sorted = new TreeMap( map );
			Iterator elements = sorted.entrySet().iterator();
			while ( elements.hasNext() ) {
				Map.Entry cur = ( Map.Entry )elements.next();
				System.out.println( cur.getValue() + "\t" + cur.getKey() );
			}
		}
		catch ( IOException e ) {
			System.err.println( "UniqueWords: " + e.getMessage() );
			System.exit( 1 );
		}

	}
}













