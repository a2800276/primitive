package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

public class ShortList {

  /**	
    default initial size of the (internal) array
  */
  private static final int    DEFAULT = 1024;
  private static final double RESIZE  = 1.8;

  private short [] underlyingArray;
  private int pos;

  public ShortList () {
    this.clear(); 
  }

  public ShortList (int size) {
    this.underlyingArray = new short[size];
  }

  public ShortList (short [] arr) {
    this.underlyingArray = arr;
    this.pos = arr.length;
  }

  public boolean add(short element) {
    ensureCapacity(1);
    this.underlyingArray[pos++] = element;
    return true;
  }

  public boolean add(short[] arr) {
    return this.addAll(arr);
  }

  public void add(int index, short element) {
    checkIndex(index);
    ensureCapacity(1);
    int toMove = this.size() - index;
    System.arraycopy(this.underlyingArray, index, this.underlyingArray, index+1, toMove); 
    this.underlyingArray[index] = element;
    ++this.pos;
  }

  public boolean addAll(ShortList list) {
    ensureCapacity  (list.size());
    System.arraycopy(list.underlyingArray, 0, this.underlyingArray, this.size(), list.size());
    this.pos += list.size();
    return true;
  }

  public boolean addAll(short [] arr) {
    return addAll(arr, 0, arr.length);
  }

  public boolean addAll(short [] arr, int offset, int len) {
    ensureCapacity  (len);
    System.arraycopy(arr, offset, this.underlyingArray, this.size(), len);
    this.pos += arr.length;
    return true;

  }

  public boolean addAll(int index, ShortList list) {
    checkIndex(index);
    ensureCapacity(list.size());
    int toMove = this.size() - index;
    System.arraycopy(this.underlyingArray, index, this.underlyingArray, index+list.size(), toMove);
    System.arraycopy(list.underlyingArray, 0, this.underlyingArray, index, list.size());
    this.pos += list.size();
    return true;
  }

  /** Slight deviation from List contract: returns itself instead of void,
   *  this allows doing: list = null == list ? new ShortList() : list.clear().
   */
  public ShortList clear() {
    if (null == this.underlyingArray || DEFAULT < this.underlyingArray.length){
      this.underlyingArray = new short[DEFAULT];
    }
    this.pos = 0;
    return this;
  }

  public boolean contains(short value) {
    return indexOf(value) != -1;
  }


  public boolean containsAll(ShortList c) {
    for (int i = 0; i!=c.size(); ++i) {
      if (!this.contains(c.get(i))) {
        return false;
      }
    }
    return true;
  }


  public short get(int index) {
    checkIndex(index);
    return this.underlyingArray [index];

  }


  public int indexOf(short value) {
    for (int i = 0; i != this.size(); ++i) {
      if (value == this.underlyingArray[i]) {
        return i;
      }
    }
    return -1;

  }

  public int lastIndexOf(short value) {
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

  public boolean remove(short value) {
    int idx = this.indexOf(value);
    if (-1 == idx) {
      return false;
    }
    this.removeIdx(idx);
    return true;
  }

  /**
   *  This deviates from the collection.List contract,
   *  because it would be the second remove(int) method
   *  in IntList and polymorphism wouldn't work...
   */
  public short removeIdx(int index) {
    checkIndex(index);
    short value = get(index);
    int toMove = this.size() - index - 1;
    if (0 != toMove) {
      System.arraycopy(this.underlyingArray, index+1, this.underlyingArray, index, toMove);
    }
    --this.pos;
    return value;
  }


  public boolean removeAll(ShortList list) {
    boolean removed = false;
    for (int i = 0; i!=list.size(); ++i) {
      short val = list.get(i);
      while (this.remove(val)) {
        removed = true;
      }
    }
    return removed;
  }


  public boolean retainAll(ShortList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public short set(int index, short element) {
    checkIndex(index);
    short ret = get(index);
    this.underlyingArray[index] = element;
    return ret;
  }


  public int size() {
    return pos;
  }

  /**
   * this currently diverges from the `List` contract in that it 
   * returns a copy instead of a list backed by the same array.
   */
  public ShortList subList(int fromIndex, int toIndex) {
    checkIndex(fromIndex);
    checkIndex(toIndex);
    if (fromIndex > toIndex){
      throw new IndexOutOfBoundsException();
    }

    int len = toIndex - fromIndex;
    short [] arr = new short[len];

    System.arraycopy(this.underlyingArray, fromIndex, arr, 0, len);
    return new ShortList(arr);
  }


  public short[] toArray() {
    short [] retArr = new short [pos];
    System.arraycopy(this.underlyingArray, 0, retArr, 0, pos);
    return retArr;

  }

  void checkIndex(int idx) {
    if (0 > idx || idx >= this.size()) {
      throw new IndexOutOfBoundsException();
    }
  }

  /**
    makes sure that there is at least room for
    <code>size</code> further elements in the internal array
    and "grows" it if necessary.
    */
  void ensureCapacity (int size) {
    int capacity = this.underlyingArray.length - pos;
    if (capacity<size){
      resize (this.underlyingArray.length+size);		
    }
  }

  /**
    "grows" the array to a new length of <code>len</code>.
    */
  void resize (int len) {
    int newLen = max(len,(int)(this.underlyingArray.length*RESIZE));
    short [] newArr = new short[newLen];
    System.arraycopy (this.underlyingArray,0,newArr,0,pos);
    this.underlyingArray = newArr;
  }

  /**
    utility to return the larger of the two provided
    parameters.
    */
  int max (int i, int j) {
    return i>j?i:j;	
  }

  class Iterator {
    private ShortList list;
    private int pos;

    public Iterator(ShortList list) {
      this.list = list;
    }

    public Iterator(ShortList list, int index) {
      checkIndex(index);
      this.list = list;
      this.pos  = index;
    }
    boolean hasNext() {
      return this.pos < list.size();
    }
    short next () {
      if (pos >= this.list.size() || pos < 0) {
        throw new java.util.NoSuchElementException();
      }
      return list.get(this.pos++);
    }
    void remove() {
      this.list.removeIdx(this.pos);
      --this.pos;
    }
  }


}

