package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

public class LongList {

  /**	
    default initial size of the (internal) array
  */
  private static final int    DEFAULT = 1024;
  private static final double RESIZE  = 1.8;

  private long [] underlyingArray;
  private int pos;

  public LongList () {
    this.clear(); 
  }

  public LongList (int size) {
    this.underlyingArray = new long[size];
  }

  public LongList (long [] arr) {
    this.underlyingArray = arr;
    this.pos = arr.length;
  }

  public boolean add(long element) {
    ensureCapacity(1);
    this.underlyingArray[pos++] = element;
    return true;
  }

  public void add(int index, long element) {
    throw new UnsupportedOperationException("TBD");
  }

  public boolean addAll(LongList list) {
    ensureCapacity  (list.size());
    System.arraycopy(list.underlyingArray, 0, this.underlyingArray, this.size(), list.size());
    this.pos += list.size();
    return true;
  }

  public boolean addAll(long [] arr) {
    return addAll(arr, 0, arr.length);
  }

  public boolean addAll(long [] arr, int offset, int len) {
    ensureCapacity  (len);
    System.arraycopy(arr, offset, this.underlyingArray, this.size(), len);
    this.pos += arr.length;
    return true;

  }


  public boolean addAll(int index, LongList list) {
    throw new UnsupportedOperationException("TBD");
  }

  public void clear() {
    if (null == this.underlyingArray || DEFAULT != this.underlyingArray.length){
      this.underlyingArray = new long[DEFAULT];
    }
    this.pos = 0;

  }

  public boolean contains(long value) {
    return indexOf(value) != -1;
  }


  public boolean containsAll(LongList c) {
    throw new UnsupportedOperationException("TBD");
  }


  public long get(int index) {
    if (!(index<size()))
      throw new ArrayIndexOutOfBoundsException();
    return this.underlyingArray [index];

  }


  public int indexOf(long value) {
    for (int i = 0; i != this.size(); ++i) {
      if (value == this.underlyingArray[i]) {
        return i;
      }
    }
    return -1;

  }

  public int lastIndexOf(long value) {
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

  public boolean remove(long value) {
    throw new UnsupportedOperationException("TBD");
  }

  /**
   *  This deviates from the collection.List contract,
   *  because it would be the second remove(int) method
   *  in IntList and polymorphism wouldn't work...
   */
  public long removeIdx(int index) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean removeAll(LongList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public boolean retainAll(LongList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public long set(int index, long element) {
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException();
    }
    long ret = get(index);
    this.underlyingArray[index] = element;
    return ret;
  }


  public int size() {
    return pos;
  }


  public LongList subList(int fromIndex, int toIndex) {
    if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex){
      throw new IndexOutOfBoundsException();
    }

    int len = toIndex - fromIndex;
    long [] arr = new long[len];

    System.arraycopy(this.underlyingArray, fromIndex, arr, 0, len);
    return new LongList(arr);
  }


  public long[] toArray() {
    long [] retArr = new long [pos];
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
    long [] newArr = new long[newLen];
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
    private LongList list;
    private int pos;

    public Iterator(LongList list) {
      this.list = list;
    }

    public Iterator(LongList list, int index) {
      if (index < 0 || index > list.size()){
        throw new IndexOutOfBoundsException();
      }
      this.list = list;
      this.pos  = index;
    }
    boolean hasNext() {
      return this.pos < list.size();
    }
    long next () {
      if (pos >= this.list.size() || pos < 0) {
        throw new java.util.NoSuchElementException();
      }
      return list.get(this.pos++);
    }
    void remove() {
      throw new UnsupportedOperationException("TBD");
    }
  }


}

