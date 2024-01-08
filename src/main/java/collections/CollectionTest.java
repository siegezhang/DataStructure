package collections;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CollectionTest {

    @Test
    public void test1() {


        MultiKeyMap<String, String> multiKeyMap = Lists.newArrayList(new Object1("a1", "b1", "c1", "value1"), new Object1("a2", "b2", "c2", "value2")).stream().collect(MultiKeyMap::new, (map, o) -> {
            map.put(o.getK1(), o.getK2(), o.getK3(), o.getV());
        }, MultiKeyMap::putAll);


        for (Map.Entry<MultiKey<? extends String>, String> entry : multiKeyMap.entrySet()) {
            System.out.println(STR. "key:\{entry.getKey().getKey(0)}-\{entry.getKey().getKey(1)}-\{entry.getKey().getKey(2)},value:\{entry.getValue()}");
        }

        System.out.println(multiKeyMap.get("a1", "b1", "c1"));
        String value = multiKeyMap.get("a1", "b1", "c2");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Object1 {
        private String k1;
        private String k2;
        private String k3;
        private String v;
    }
}
