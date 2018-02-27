package basic.bitmap;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



/**
 * Created by siege on 2017/9/2.
 */
class BitSetTest {
    private static BitSet bitSet1;
    private static BitSet bitSet2;
    private static int[] data={2,42,5,6,18,33,15,25,31,28,37};

    @BeforeAll
    @DisplayName("相关成员变量初始化")
    static void init(){
        bitSet1=new BitSet();
        bitSet2=new BitSet(getMaxValue(data));
    }
    @Test
    @DisplayName("BitSet基本特性测试")
    void basicPropertiesTest() {

        assertTrue(bitSet1.isEmpty());
        assertEquals(64,bitSet1.size());


        bitSet1.set(0);
        assertFalse(bitSet1.isEmpty());
        bitSet1.clear();
        assertTrue(bitSet1.isEmpty());
        assertEquals(64,bitSet1.size());

        bitSet1.set(1);
        assertEquals(64,bitSet1.size());

        assertFalse(bitSet1.get(65));
        assertEquals(64,bitSet1.size());

        bitSet1.set(65);
        assertEquals(128,bitSet1.size());

    }

    @Test
    @DisplayName("BitSet来实现对不重复的数据进行排序")
    void sortTest(){
        assertEquals(64,bitSet2.size());
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
                }
            }
        }
    }

    private static void putValueIntoBitSet(int[] data, BitSet bitSet2) {
        for (int item:data){
            bitSet2.set(item);
        }
    }

    private static int getMaxValue(int[] data) {
        int temp=data[0];
        for(int item:data){
            temp=temp<item?item:temp;
        }
        System.out.println("max value:"+temp);
        return temp;
    }
}
