package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

import java.util.Random;

public class CharList {

  /**	
    default initial size of the (internal) array
  */
  private static final int    DEFAULT = 1024;
  private static final double RESIZE  = 1.8;

  private char [] underlyingArray;
  private int pos;

  public CharList () {
    this.clear(); 
  }

  public CharList (int size) {
    this.underlyingArray = new char[size];
  }

  public CharList (char [] arr) {
    this.underlyingArray = arr;
    this.pos = arr.length;
  }

  public boolean add(char element) {
    ensureCapacity(1);
    this.underlyingArray[pos++] = element;
    return true;
  }

  public void add(int index, char element) {
    throw new UnsupportedOperationException("TBD");
  }

  public boolean addAll(CharList list) {
    ensureCapacity  (list.size());
    System.arraycopy(list.underlyingArray, 0, this.underlyingArray, this.size(), list.size());
    this.pos += list.size();
    return true;
  }

  public boolean addAll(char [] arr) {
    return addAll(arr, 0, arr.length);
  }

  public boolean addAll(char [] arr, int offset, int len) {
    ensureCapacity  (len);
    System.arraycopy(arr, offset, this.underlyingArray, this.size(), len);
    this.pos += arr.length;
    return true;

  }


  public boolean addAll(int index, CharList list) {
    throw new UnsupportedOperationException("TBD");
  }

  public void clear() {
    if (null == this.underlyingArray || DEFAULT != this.underlyingArray.length){
      this.underlyingArray = new char[DEFAULT];
    }
    this.pos = 0;

  }

  public boolean contains(char value) {
    return indexOf(value) != -1;
  }


  public boolean containsAll(CharList c) {
    throw new UnsupportedOperationException("TBD");
  }


  public char get(int index) {
    if (!(index<size()))
      throw new ArrayIndexOutOfBoundsException();
    return this.underlyingArray [index];

  }


  public int indexOf(char value) {
    for (int i = 0; i != this.size(); ++i) {
      if (value == this.underlyingArray[i]) {
        return i;
      }
    }
    return -1;

  }

  public int lastIndexOf(char value) {
    for (int i = this.size()-1 ; i != -1; --i) {
      if (value == this.underlyingArray[i]) {
        return i;
      }
    }
    return -1;
  }



  public boolean isEmpty() {
    return this.size() == 0;
  }


  public Iterator iterator() {
    return new Iterator(this);
  }

  public Iterator iterator(int index) {
    return new Iterator(this, index);
  }

  public boolean remove(char value) {
    throw new UnsupportedOperationException("TBD");
  }

  /**
   *  This deviates from the collection.List contract,
   *  because it would be the second remove(int) method
   *  in IntList and polymorphism wouldn't work...
   */
  public char removeIdx(int index) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean removeAll(CharList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean retainAll(CharList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public char set(int index, char element) {
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException();
    }
    char ret = get(index);
    this.underlyingArray[index] = element;
    return ret;
  }


  public int size() {
    return pos;
  }


  public CharList subList(int fromIndex, int toIndex) {
    if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex){
      throw new IndexOutOfBoundsException();
    }

    int len = toIndex - fromIndex;
    char [] arr = new char[len];

    System.arraycopy(this.underlyingArray, fromIndex, arr, 0, len);
    return new CharList(arr);
  }


  public char[] toArray() {
    char [] retArr = new char [pos];
    System.arraycopy(this.underlyingArray, 0, retArr, 0, pos);
    return retArr;

  }



  /**
    makes sure that there is at least room for
    <code>size</code> further elements in the internal array
    and "grows" it if necessary.
    */
  private void ensureCapacity (int size) {
    int capacity = this.underlyingArray.length - pos;
    if (capacity<size){
      resize (this.underlyingArray.length+size);		
    }
  }

  /**
    "grows" the array to a new length of <code>len</code>.
    */
  private void resize (int len) {
    int newLen = max(len,(int)(this.underlyingArray.length*RESIZE));
    char [] newArr = new char[newLen];
    System.arraycopy (this.underlyingArray,0,newArr,0,pos);
    this.underlyingArray = newArr;
  }

  /**
    utility to return the larger of the two provided
    parameters.
    */
  private int max (int i, int j) {
    return i>j?i:j;	
  }

  class Iterator {
    private CharList list;
    private int pos;

    public Iterator(CharList list) {
      this.list = list;
    }

    public Iterator(CharList list, int index) {
      if (index < 0 || index > list.size()){
        throw new IndexOutOfBoundsException();
      }
      this.list = list;
      this.pos  = index;
    }
    boolean hasNext() {
      return this.pos < list.size();
    }
    char next () {
      if (pos >= this.list.size() || pos < 0) {
        throw new java.util.NoSuchElementException();
      }
      return list.get(this.pos++);
    }
    void remove() {
      throw new UnsupportedOperationException("TBD");
    }
  }

  private static void p (Object o) {
    System.out.println(o);
  }
  public static void main (String [] args) {
    
    long start        = System.currentTimeMillis();

    CharList list = new CharList();
    Random       rand = new Random(0L);
    byte[]        bla = new byte[1];

    char    value ;
     
    for (int i=0; i!=250000; ++i) {
      value = (char)rand.nextInt();
      list.add(value);
    }
    
    char[] arr = list.toArray();
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

