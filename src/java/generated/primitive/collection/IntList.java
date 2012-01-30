package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

public class IntList {

  /**	
    default initial size of the (internal) array
  */
  private static final int    DEFAULT = 1024;
  private static final double RESIZE  = 1.8;

  private int [] underlyingArray;
  private int pos;

  public IntList () {
    this.clear(); 
  }

  public IntList (int size) {
    this.underlyingArray = new int[size];
  }

  public IntList (int [] arr) {
    this.underlyingArray = arr;
    this.pos = arr.length;
  }

  public boolean add(int element) {
    ensureCapacity(1);
    this.underlyingArray[pos++] = element;
    return true;
  }

  public void add(int index, int element) {
    checkIndex(index);
    ensureCapacity(1);
    int toMove = this.size() - index;
    System.arraycopy(this.underlyingArray, index, this.underlyingArray, index+1, toMove); 
    this.underlyingArray[index] = element;
    ++this.pos;
  }

  public boolean addAll(IntList list) {
    ensureCapacity  (list.size());
    System.arraycopy(list.underlyingArray, 0, this.underlyingArray, this.size(), list.size());
    this.pos += list.size();
    return true;
  }

  public boolean addAll(int [] arr) {
    return addAll(arr, 0, arr.length);
  }

  public boolean addAll(int [] arr, int offset, int len) {
    ensureCapacity  (len);
    System.arraycopy(arr, offset, this.underlyingArray, this.size(), len);
    this.pos += arr.length;
    return true;

  }

  public boolean addAll(int index, IntList list) {
    checkIndex(index);
    ensureCapacity(list.size());
    int toMove = this.size() - index;
    System.arraycopy(this.underlyingArray, index, this.underlyingArray, index+list.size(), toMove);
    System.arraycopy(list.underlyingArray, 0, this.underlyingArray, index, list.size());
    this.pos += list.size();
    return true;
  }

  /** Slight deviation from List contract: returns itself instead of void,
   *  this allows doing: list = null == list ? new IntList() : list.clear().
   */
  public IntList clear() {
    if (null == this.underlyingArray || DEFAULT < this.underlyingArray.length){
      this.underlyingArray = new int[DEFAULT];
    }
    this.pos = 0;
    return this;
  }

  public boolean contains(int value) {
    return indexOf(value) != -1;
  }


  public boolean containsAll(IntList c) {
    for (int i = 0; i!=c.size(); ++i) {
      if (!this.contains(c.get(i))) {
        return false;
      }
    }
    return true;
  }


  public int get(int index) {
    checkIndex(index);
    return this.underlyingArray [index];

  }


  public int indexOf(int value) {
    for (int i = 0; i != this.size(); ++i) {
      if (value == this.underlyingArray[i]) {
        return i;
      }
    }
    return -1;

  }

  public int lastIndexOf(int value) {
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

  public boolean remove(int value) {
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
  public int removeIdx(int index) {
    checkIndex(index);
    int value = get(index);
    int toMove = this.size() - index - 1;
    if (0 != toMove) {
      System.arraycopy(this.underlyingArray, index+1, this.underlyingArray, index, toMove);
    }
    --this.pos;
    return value;
  }


  public boolean removeAll(IntList list) {
    boolean removed = false;
    for (int i = 0; i!=list.size(); ++i) {
      int val = list.get(i);
      while (this.remove(val)) {
        removed = true;
      }
    }
    return removed;
  }


  public boolean retainAll(IntList list) {
    throw new UnsupportedOperationException("TBD");
  }


  public int set(int index, int element) {
    checkIndex(index);
    int ret = get(index);
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
  public IntList subList(int fromIndex, int toIndex) {
    checkIndex(fromIndex);
    checkIndex(toIndex);
    if (fromIndex > toIndex){
      throw new IndexOutOfBoundsException();
    }

    int len = toIndex - fromIndex;
    int [] arr = new int[len];

    System.arraycopy(this.underlyingArray, fromIndex, arr, 0, len);
    return new IntList(arr);
  }


  public int[] toArray() {
    int [] retArr = new int [pos];
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
    int [] newArr = new int[newLen];
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
    private IntList list;
    private int pos;

    public Iterator(IntList list) {
      this.list = list;
    }

    public Iterator(IntList list, int index) {
      checkIndex(index);
      this.list = list;
      this.pos  = index;
    }
    boolean hasNext() {
      return this.pos < list.size();
    }
    int next () {
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

