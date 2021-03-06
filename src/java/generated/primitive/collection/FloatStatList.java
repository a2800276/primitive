package primitive.collection;

/* DO NOT EDIT THIS FILE, IT IS GENERATED! */

import java.util.Random;

public class FloatStatList extends FloatList {

  public void each (FloatFunction func) {
    for(int i=0; i!=this.size(); ++i) {
      func.apply(this.get(i));
    }  
  }

  public float reduce(float initial, FloatFunction func) {
    float memo = initial;
    for(int i=0; i!=this.size(); ++i) {
      memo = func.accumulate(memo, this.get(i));
    }
    return memo;
    
  } 

  public float sum () {
    FloatFunction f = new FloatFunction() {
      public float accumulate(float memo, float value) {
        return (float)(memo+value);
      }
    };

    return this.reduce((float)0,f);
  }

  public FloatStats stats () {
    
    final FloatStats stats = new FloatStats();

    FloatFunction f = new FloatFunction() {
      public void apply(float value) {
        
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
    
    f = new FloatFunction() {
      public void apply(float value) {
        stats.var += Math.pow(stats.avg - (double)value, 2);
      }
    };
    
    this.each(f);

    stats.var  = stats.var / (double)stats.n;
    stats.stdd = Math.pow(stats.var, 0.5);
    return stats;

    
  }

  static FloatStatList random(int count) {
    Random       rand = new Random(0L);
    FloatStatList slist = new FloatStatList();
    float value;
    for (int i=0; i!=count; ++i) {
      value = rand.nextFloat();
      slist.add(value);
    }
    return slist;
  }

  class FloatStats {
    public int n;
    public float min = Float.MAX_VALUE;
    public float max = Float.MIN_VALUE;
    public float sum;
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
    FloatStatList list = FloatStatList.random(10);
    
    list.each(new FloatFunction() {
      public void apply(float value) {
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

