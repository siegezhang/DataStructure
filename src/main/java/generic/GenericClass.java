package generic;

import java.util.List;
import java.util.Map;

public class GenericClass<T> {
        private List<T> list;                     // 2
        private Map<String, T> map;               // 3

        public <U> U genericMethod(Map<T, U> m) { // 4
            return null;
    }
}
