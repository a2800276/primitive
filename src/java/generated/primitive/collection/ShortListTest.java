package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

import java.util.Random;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShortListTest extends TestCase {
  Random rand = new Random(); 
  @Test public void testAdd () {
    ShortList list = new ShortList();
    short []  arr  = new short[1000];
    short element = 0;
    for (int i = 0 ; i!= 1000; ++i) {
      element = (short)rand.nextInt(32000);
      list.add(element);
      arr[i] = element;
    }
    assertEquals(1000, list.size());
    assertTrue(arrEquals(arr, list.toArray()));
  }

  @Test public void testAddIdx() {
    ShortList list = new ShortList();
    for (int i = 0; i!=50; ++i) {
      list.add((short)rand.nextInt(32000));
    }
    short val  = 0;
    short test = list.get(0);
    list.add(0, val);
    assertEquals(val, list.get(0));
    assertEquals(test, list.get(1));

    int i = list.size()-1;
    test = list.get(i);
    list.add(i, val);
    assertEquals(val, list.get(i));
    assertEquals(test, list.get(i+1));

    i = list.size()/2;
    test = list.get(i);
    list.add(i, val);
    assertEquals(val, list.get(i));
    assertEquals(test, list.get(i+1));


  }

  static boolean arrEquals (short [] arr1, short [] arr2) {
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

    ShortList list = new ShortList();
    Random       rand = new Random(0L);
    byte[]        bla = new byte[1];

    short    value ;
     
    for (int i=0; i!=250000; ++i) {
      value = (short)rand.nextInt(32000);
      list.add(value);
    }
    
    short[] arr = list.toArray();
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

