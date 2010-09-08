package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

import java.util.Random;

public class ByteList {

  /**	
    default initial size of the (internal) array
  */
  private static final int    DEFAULT = 1024;
  private static final double RESIZE  = 1.8;

  private byte [] underlyingArray;
  private int pos;

  public ByteList () {
    this.clear(); 
  }

  public ByteList (int size) {
    this.underlyingArray = new byte[size];
  }

  public ByteList (byte [] arr) {
    this.underlyingArray = arr;
    this.pos = arr.length;
  }

  public boolean add(byte element) {
    ensureCapacity(1);
    this.underlyingArray[pos++] = element;
    return true;
  }

  public void add(int index, byte element) {
    throw new UnsupportedOperationException("TBD");
  }

  public boolean addAll(ByteList list) {
    byte [] temp = new byte[this.size() + list.size()];
    System.arraycopy(underlyingArray,      0, temp, 0,           this.size());
    System.arraycopy(list.underlyingArray, 0, temp, this.size(), list.size());
    this.underlyingArray = temp;
    return true;
  }

  public boolean addAll(byte [] arr) {
    byte [] temp = new byte[this.size() + arr.length];
    p(temp.length);
    System.arraycopy(underlyingArray, 0, temp, 0,           this.size());
    System.arraycopy(arr            , 0, temp, this.size(), arr.length);
    this.underlyingArray =  temp;
    this.pos             += arr.length;
    return true;
    
  }


  public boolean addAll(int index, ByteList list) {
    throw new UnsupportedOperationException("TBD");
  }

  public void clear() {
    this.underlyingArray = new byte[DEFAULT];
    this.pos = 0;

  }

  public boolean contains(byte value) {
    return indexOf(value) != -1;
  }


  public boolean containsAll(ByteList c) {
    throw new UnsupportedOperationException("TBD");
  }


  public byte get(int index) {
    if (!(index<size()))
      throw new ArrayIndexOutOfBoundsException();
    return this.underlyingArray [index];

  }


  public int indexOf(byte value) {
    for (int i = 0; i != this.size(); ++i) {
      if (value == this.underlyingArray[i]) {
        return i;
      }
    }
    return -1;

  }

  public int lastIndexOf(byte value) {
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

  public boolean remove(byte value) {
    throw new UnsupportedOperationException("TBD");
  }

  /**
   *  This deviates from the collection List contract,
   *  because it would be the second remove(int) method
   *  in IntList and polymorphism wouldn't work...
   */
  public byte removeIdx(int index) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean removeAll(ByteList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean retainAll(ByteList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public byte set(int index, byte element) {
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException();
    }
    byte ret = get(index);
    this.underlyingArray[index] = element;
    return ret;
  }


  public int size() {
    return pos;
  }


  public ByteList subList(int fromIndex, int toIndex) {
    if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex){
      throw new IndexOutOfBoundsException();
    }

    int len = toIndex - fromIndex;
    byte [] arr = new byte[len];

    System.arraycopy(this.underlyingArray, fromIndex, arr, 0, len);
    return new ByteList(arr);
  }


  public byte[] toArray() {
    byte [] retArr = new byte [pos];
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
    byte [] newArr = new byte[newLen];
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
    private ByteList list;
    private int pos;

    public Iterator(ByteList list) {
      this.list = list;
    }

    public Iterator(ByteList list, int index) {
      if (index < 0 || index > list.size()){
        throw new IndexOutOfBoundsException();
      }
      this.list = list;
      this.pos  = index;
    }
    boolean hasNext() {
      return this.pos < list.size();
    }
    byte next () {
      if (pos >= this.list.size() || pos < 0) {
        throw new java.util.NoSuchElementException();
      }
      return list.get(this.pos++);
    }
    void remove() {
      throw new UnsupportedOperationException("TBD");
    }
  }

  public static void p (Object o) {
    System.out.println(o);
  }
  public static void main (String [] args) {
    
    long start        = System.currentTimeMillis();

    ByteList list = new ByteList();
    Random       rand = new Random(0L);
    byte[]        bla = new byte[1];

    byte    value ;
     
    for (int i=0; i!=120480; ++i) {
      value = (byte)rand.nextInt(256);
      list.add(value);
    }
    
    byte[] arr = list.toArray();
    list.addAll(arr);


    p("ok...: "+list.size()+" in: "+(System.currentTimeMillis()-start));

    start = System.currentTimeMillis();

    java.util.List list2 = new java.util.LinkedList();
    
    for (int i=0; i!=120480; ++i) {
      int value2 = rand.nextInt();
      list2.add(new Integer(value2));
    }
    list2.addAll(list2);
    p("ok...: "+list2.size()+" in: "+(System.currentTimeMillis()-start));


  }

}

