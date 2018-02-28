package basic.bloomfilter;

import java.util.BitSet;

/**
 * Created by siege on 2017/9/3.
 */
public class BloomFilter {
    private static final int DEFAULT_SIZE=1<<25;

    private static BitSet bits=new BitSet(DEFAULT_SIZE);
    private static SimpleHash[] func;

    public BloomFilter(int[] seeds) {
        func=new SimpleHash[seeds.length];
        for (int i = 0; i < seeds.length; i++) {
            func[i]=new SimpleHash(DEFAULT_SIZE,seeds[i]);
        }
    }

    public void add(String value){
        for(SimpleHash f:func){
            bits.set(f.hash(value));
        }
    }

    public boolean contains(String value){
        for(SimpleHash f:func){
            if (!bits.get(f.hash(value)))
                return false;
        }
        return true;
    }

    private static class SimpleHash{
        private int cap;
        private int seed;

        SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        int hash(String value){
            int result=0;
            int len=value.length();
            for (int i = 0; i < len; i++) {
                result=seed*result+value.charAt(i);
            }
            return (cap-1)&result;
        }
    }
}
