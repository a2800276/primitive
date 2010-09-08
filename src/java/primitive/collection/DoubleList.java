package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

import java.util.Random;

public class DoubleList {

  /**	
    default initial size of the (internal) array
  */
  private static final int    DEFAULT = 1024;
  private static final double RESIZE  = 1.8;

  private double [] underlyingArray;
  private int pos;

  public DoubleList () {
    this.clear(); 
  }

  public DoubleList (int size) {
    this.underlyingArray = new double[size];
  }

  public DoubleList (double [] arr) {
    this.underlyingArray = arr;
    this.pos = arr.length;
  }

  public boolean add(double element) {
    ensureCapacity(1);
    this.underlyingArray[pos++] = element;
    return true;
  }

  public void add(int index, double element) {
    throw new UnsupportedOperationException("TBD");
  }

  public boolean addAll(DoubleList list) {
    double [] temp = new double[this.size() + list.size()];
    System.arraycopy(underlyingArray,      0, temp, 0,           this.size());
    System.arraycopy(list.underlyingArray, 0, temp, this.size(), list.size());
    this.underlyingArray = temp;
    return true;
  }

  public boolean addAll(double [] arr) {
    double [] temp = new double[this.size() + arr.length];
    p(temp.length);
    System.arraycopy(underlyingArray, 0, temp, 0,           this.size());
    System.arraycopy(arr            , 0, temp, this.size(), arr.length);
    this.underlyingArray =  temp;
    this.pos             += arr.length;
    return true;
    
  }


  public boolean addAll(int index, DoubleList list) {
    throw new UnsupportedOperationException("TBD");
  }

  public void clear() {
    this.underlyingArray = new double[DEFAULT];
    this.pos = 0;

  }

  public boolean contains(double value) {
    return indexOf(value) != -1;
  }


  public boolean containsAll(DoubleList c) {
    throw new UnsupportedOperationException("TBD");
  }


  public double get(int index) {
    if (!(index<size()))
      throw new ArrayIndexOutOfBoundsException();
    return this.underlyingArray [index];

  }


  public int indexOf(double value) {
    for (int i = 0; i != this.size(); ++i) {
      if (value == this.underlyingArray[i]) {
        return i;
      }
    }
    return -1;

  }

  public int lastIndexOf(double value) {
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

  public boolean remove(double value) {
    throw new UnsupportedOperationException("TBD");
  }

  /**
   *  This deviates from the collection List contract,
   *  because it would be the second remove(int) method
   *  in IntList and polymorphism wouldn't work...
   */
  public double removeIdx(int index) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean removeAll(DoubleList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean retainAll(DoubleList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public double set(int index, double element) {
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException();
    }
    double ret = get(index);
    this.underlyingArray[index] = element;
    return ret;
  }


  public int size() {
    return pos;
  }


  public DoubleList subList(int fromIndex, int toIndex) {
    if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex){
      throw new IndexOutOfBoundsException();
    }

    int len = toIndex - fromIndex;
    double [] arr = new double[len];

    System.arraycopy(this.underlyingArray, fromIndex, arr, 0, len);
    return new DoubleList(arr);
  }


  public double[] toArray() {
    double [] retArr = new double [pos];
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
    double [] newArr = new double[newLen];
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
    private DoubleList list;
    private int pos;

    public Iterator(DoubleList list) {
      this.list = list;
    }

    public Iterator(DoubleList list, int index) {
      if (index < 0 || index > list.size()){
        throw new IndexOutOfBoundsException();
      }
      this.list = list;
      this.pos  = index;
    }
    boolean hasNext() {
      return this.pos < list.size();
    }
    double next () {
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

    DoubleList list = new DoubleList();
    Random       rand = new Random(0L);
    byte[]        bla = new byte[1];

    double    value ;
     
    for (int i=0; i!=120480; ++i) {
      value = rand.nextDouble();
      list.add(value);
    }
    
    double[] arr = list.toArray();
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

