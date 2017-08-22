package basic.skiplist;

/**
 * Created by siege on 2017/8/22.
 */
public class SkipListEntry {
    public String key;
    public Integer value;

    public SkipListEntry up,down,left,right;

    public  static String negInf ="-oo";
    public  static String posInf ="+oo";

    public SkipListEntry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
