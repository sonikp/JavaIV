//package edu.ucsd.lecture2;
package week2;

import java.lang.annotation.*;

//example user input: edu.ucsd.lecture2.Suggester

//@Author(@Name(first="James",last="Heliotis"))

@Illustrate(
        {Illustrate.Feature.enumeration,Illustrate.Feature.forLoop})
public class Suggester {
    @SuppressWarnings({"unchecked"}) // not yet supported 
    public static void main( String[] args ) {
        try {
           java.util.Scanner userInput =
                                new java.util.Scanner( System.in );
           System.out.print( "In what class are you interested? " );
           Class theClass = Class.forName( userInput.next() );
           Illustrate ill =
              (Illustrate)theClass.getAnnotation( Illustrate.class );
           if ( ill != null ) {
                System.out.println( "Look at this class if you'd " +
                                    " like to see examples of" );
                for ( Illustrate.Feature f : ill.value() ) {
                    System.out.println( "\t" + f );
                }
            }
            else {
                System.out.println(
                          "That class will teach you nothing." );
            }
        }
        catch( ClassNotFoundException cnfe ) {
            System.err.println( "I could not find a class named \"" +
                                cnfe.getMessage() + "\"." );
            System.err.println( "Are you sure about that name?" );
        }
    }
}
