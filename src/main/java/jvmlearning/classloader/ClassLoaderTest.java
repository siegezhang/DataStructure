package jvmlearning.classloader;

import com.google.common.reflect.ClassPath;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Set;

public class ClassLoaderTest {
    public static void main(String[] args) throws IOException {
        ClassPath classPath = ClassPath.from(ClassLoaderTest.class.getClassLoader());
        Set<ClassPath.ClassInfo> classes = classPath.getAllClasses();
        classes.forEach(System.out::println);
        Assertions.assertTrue(4 < classes.size());
    }
}
