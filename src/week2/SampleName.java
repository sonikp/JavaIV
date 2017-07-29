//package edu.ucsd.lecture2;
package week2;


import java.lang.reflect.*;
import java.awt.*;
public class SampleName {
   Button b;
   Class c;

   public SampleName(){
   }

   public static void main (String[] args) {
      SampleName sn = new SampleName();
      sn.b= new Button();   
      sn.printName(sn.b);
   }
   private void printName(Object o) {
      c = o.getClass();
      String s = c.getName();
      System.out.println(s); //prints out class name
      c = c.getSuperclass();
      s = c.getName();
      System.out.println(s); //prints out superclass name
   }
}

