package basic.bitmap;

import java.util.Arrays;
import java.util.BitSet;


/**
 * Created by siege on 2017/9/2.
 */
public class BitSetTest {
    public static void main(String[] args) {
        BitSet bitSet1=new BitSet();
        System.out.println(bitSet1.isEmpty()+"---"+bitSet1.size());
        bitSet1.set(0);
        System.out.println(bitSet1.isEmpty()+"---"+bitSet1.size());
        bitSet1.set(1);
        System.out.println(bitSet1.isEmpty()+"---"+bitSet1.size());
        System.out.println(bitSet1.get(65));
        System.out.println(bitSet1.isEmpty()+"--"+bitSet1.size());
        bitSet1.set(65);
        System.out.println(bitSet1.isEmpty()+"--"+bitSet1.size());
        System.out.println("--------------------------");


        //bitmap来实现对不重复的数据进行排序
        int[] data={2,42,5,6,18,33,15,25,31,28,37};

        BitSet bitSet2=new BitSet(getMaxValue(data));
        System.out.println("size="+bitSet2.size());
        putValueIntoBitSet(data,bitSet2);
        sort(bitSet2,data);
        System.out.println(Arrays.toString(data));
    }

    private static void sort(BitSet bitSet2, int[] data) {
        int length=data.length;
        int j=0;
        for (int i = 0; i < length; i++) {
            while(true){
                if (bitSet2.get(j++)){
                    data[i]=j-1;
                    break;
                }else
                    continue;
            }

        }
    }

    private static void putValueIntoBitSet(int[] data, BitSet bitSet2) {
        for (int i = 0; i <data.length ; i++) {
            bitSet2.set(data[i],true);
        }
    }

    private static int getMaxValue(int[] data) {
        int temp=data[0];
        for (int i = 0; i <data.length ; i++) {
            if (temp<data[i])
                temp=data[i];
        }
        System.out.println("max value:"+temp);
        return temp;
    }
}
