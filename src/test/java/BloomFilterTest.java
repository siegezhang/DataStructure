import basic.bloomfilter.BloomFilter;
import org.junit.BeforeAll;
import org.junit.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assertions.assertFalse;
import static org.junit.Assertions.assertTrue;

public class BloomFilterTest {
    private static BloomFilter filter;


    @BeforeAll
    @DisplayName("BloomFilter种子初始化")
    static void init(){
        int[] seeds=new int[]{5, 7, 11, 13, 31, 37, 61 };
        filter=new BloomFilter(seeds);
    }

    @Test
    @DisplayName("BloomFilter基本特性测试")
    void propertiesTest(){
        filter.add("hello");
        assertTrue(filter.contains("hello"));
        assertFalse(filter.contains("world"));
    }



}
