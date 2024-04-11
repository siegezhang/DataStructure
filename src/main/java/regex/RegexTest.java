package regex;

import org.junit.jupiter.api.*;


public class RegexTest {

    @BeforeAll
    @DisplayName("表明在所有测试方法运行之前执行的方法")
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    @DisplayName("表明在所有测试方法运行之后执行的方法")
    static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach
    @DisplayName("每条用例开始时执行")
    void start() {
        System.out.println("start");
    }

    @AfterEach
    @DisplayName("每条用例结束时执行")
    void end() {
        System.out.println("end");
    }

    @Test
    @DisplayName("运行一组断言")
    void assertAllCase() {
        Assertions.assertAll("groupAssert", () -> Assertions.assertEquals(2, 1 + 1), () -> Assertions.assertTrue(1 > 0));
    }

    @Disabled
    @Test
    @DisplayName("disabled test")
    public void ignoredTest() {
        System.out.println("This test is disabled");
    }
}
