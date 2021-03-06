package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

import java.util.Random;

public class LongStatList extends LongList {

  public void each (LongFunction func) {
    for(int i=0; i!=this.size(); ++i) {
      func.apply(this.get(i));
    }  
  }

  public long reduce(long initial, LongFunction func) {
    long memo = initial;
    for(int i=0; i!=this.size(); ++i) {
      memo = func.accumulate(memo, this.get(i));
    }
    return memo;
    
  } 

  public long sum () {
    LongFunction f = new LongFunction() {
      public long accumulate(long memo, long value) {
        return (long)(memo+value);
      }
    };

    return this.reduce((long)0,f);
  }

  public LongStats stats () {
    
    final LongStats stats = new LongStats();

    LongFunction f = new LongFunction() {
      public void apply(long value) {
        
        if (value < stats.min) {
          stats.min = value;
        } 
        if (value > stats.max) {
          stats.max = value;
        }
        stats.sum += value;
      }
    
    };
     
    this.each(f);
    
    stats.n   = this.size();
    stats.avg = stats.sum / (double)stats.n;
    
    f = new LongFunction() {
      public void apply(long value) {
        stats.var += Math.pow(stats.avg - (double)value, 2);
      }
    };
    
    this.each(f);

    stats.var  = stats.var / (double)stats.n;
    stats.stdd = Math.pow(stats.var, 0.5);
    return stats;

    
  }

  static LongStatList random(int count) {
    Random       rand = new Random(0L);
    LongStatList slist = new LongStatList();
    long value;
    for (int i=0; i!=count; ++i) {
      value = rand.nextLong();
      slist.add(value);
    }
    return slist;
  }

  class LongStats {
    public int n;
    public long min = Long.MAX_VALUE;
    public long max = Long.MIN_VALUE;
    public long sum;
    public double avg;
    public double stdd;
    public double var;
    public String toString() {
      StringBuilder b = new StringBuilder();
      b.append("n    = "); b.append(n);   b.append("\n");
      b.append("min  = "); b.append(min); b.append("\n");
      b.append("max  = "); b.append(max); b.append("\n");
      b.append("sum  = "); b.append(sum); b.append("\n");
      b.append("avg  = "); b.append(avg); b.append("\n");
      b.append("stdd = "); b.append(stdd);b.append("\n");
      b.append("var  = "); b.append(var); b.append("\n");
      return b.toString();
    }
  }
  
  private static void p(Object o) {
    System.out.println(o);
  }
  public static void main (String [] args) {
    LongStatList list = LongStatList.random(10);
    
    list.each(new LongFunction() {
      public void apply(long value) {
        p(value);
      }
    });
    p(list.stats());
    p(list.sum());

    IntStatList l = new IntStatList();
    for (int i =1; i<101; ++i) {
      l.add(i);
    }
    p(l.stats());
    p(l.sum());
  }

}

