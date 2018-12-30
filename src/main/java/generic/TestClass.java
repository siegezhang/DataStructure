package generic;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        List<String> list = null;       // 1
        list = new ArrayList<String>(); // 2
        for (int i = 0; i < 10; i++) ;
    }
}
