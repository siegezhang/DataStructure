import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入字符串:");
        String str = sc.next();
        System.out.println(str);
        System.out.println(str.hashCode());

    }
}
