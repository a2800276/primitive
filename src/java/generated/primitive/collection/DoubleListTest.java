package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

import java.util.Random;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleListTest extends TestCase {
  Random rand = new Random(); 
  @Test public void testAdd () {
    DoubleList list = new DoubleList();
    double []  arr  = new double[1000];
    double element = 0.0d;
    for (int i = 0 ; i!= 1000; ++i) {
      element = rand.nextDouble();
      list.add(element);
      arr[i] = element;
    }
    assertEquals(1000, list.size());
    assertTrue(arrEquals(arr, list.toArray()));
  }

  static boolean arrEquals (double [] arr1, double [] arr2) {
    // unfortunately, junit provides no assertArrayEquals for:
    // boolean [], float[] and double[]
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0 ; i!= arr1.length; ++i) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }
  static void p (Object o) {
    System.out.println(o);
  }
  public static void main (String [] args) {
    
    long start        = System.currentTimeMillis();

    DoubleList list = new DoubleList();
    Random       rand = new Random(0L);
    byte[]        bla = new byte[1];

    double    value ;
     
    for (int i=0; i!=250000; ++i) {
      value = rand.nextDouble();
      list.add(value);
    }
    
    double[] arr = list.toArray();
    list.addAll(arr);


    p("ok...: "+list.size()+" in: "+(System.currentTimeMillis()-start));

    start = System.currentTimeMillis();

    java.util.List list2 = new java.util.ArrayList();
    
    for (int i=0; i!=250000; ++i) {
      int value2 = rand.nextInt();
      list2.add(new Integer(value2));
    }
    list2.addAll(list2);
    p("ok...: "+list2.size()+" in: "+(System.currentTimeMillis()-start));


  }

}

