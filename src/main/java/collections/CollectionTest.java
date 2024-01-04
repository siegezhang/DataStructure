package collections;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CollectionTest {

    @Test
    public void test1() {
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();

        multiKeyMap.put("a1", "b1", "c1", "value1");
        multiKeyMap.put("a2", "b2", "c2", "value1");

        for (Map.Entry<MultiKey<? extends String>, String> entry : multiKeyMap.entrySet()) {
            System.out.println(STR. "\{entry.getKey().getKey(0)}   \{entry.getKey().getKey(1)}  \{entry.getKey().getKey(2)} value: \{entry.getValue()}");
        }
    }
}
