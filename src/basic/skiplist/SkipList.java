package basic.skiplist;

import java.util.Random;

/**
 * Created by siege on 2017/8/22.
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
 */
public class SkipList {
    public SkipListEntry head;
    public SkipListEntry tail;

    public SkipList() {
        SkipListEntry p1, p2;
        p1 = new SkipListEntry(SkipListEntry.negInf, null);
        p2 = new SkipListEntry(SkipListEntry.posInf, null);

        head = p1;
        tail = p2;

        p1.right = p2;
        p2.left = p1;

        n = 0;
        h = 0;
        r = new Random();
    }

    public int n;       // number of entries in the Skip List
    public int h;       // Height

    /**
     * Coin toss,The Random object r is used
     * to determine the height of a newly added entry
     * We use r to simulate a coin toss experiment
     */
    public Random r;


    /**
     * 数据查找
     * <p>
     * Search RIGHT until you find a LARGER entry
     * <p>
     * E.g.: k = 34
     * <p>
     * 10 ---> 20 ---> 30 ---> 40
     *           ^
     *           |
     *         p must stop here
     * p.right.key = 40
     */
    public SkipListEntry findEntry(String k) {
        SkipListEntry p;
        p = head;
        while (true) {
            while (p.right.key != SkipListEntry.posInf && (p.right.key).compareTo(k) <= 0)
                p = p.right;

            //Go down one level if you can...
            if (p.down!=null)
                p=p.down;
            else
                break;
        }
        return(p);
    }

    public Integer get(String k){
        SkipListEntry p;
        p=findEntry(k);
        if (k.equals(p.key))
            return p.value;
        else
            return null;
    }

    public Integer put(String k,Integer v){
        SkipListEntry p,q;
        int i;

        p=findEntry(k);
        if (k.equals(p.key)){
            Integer old=p.value;
            p.value=v;
            return old;
        }

        q=new SkipListEntry(k,v);
        q.left=p;
        q.right=p.right;
        p.right.left=q;
        p.right=q;

        i=0;
        while (r.nextDouble()<0.5) {
            if (i >= h) {
                //Create a new empty TOP layer 省略
            }

            while (p.up == null)
                p = p.left;
            p = p.up;
            SkipListEntry e = new SkipListEntry(k, null);
            e.left = p;
            e.right = p.right;
            e.down = q;

            p.right.left = e;
            p.right = e;
            q.up = e;

            q = e;

            i = i + 1;
        }
        n=n+1;
        return  null;
    }

}
