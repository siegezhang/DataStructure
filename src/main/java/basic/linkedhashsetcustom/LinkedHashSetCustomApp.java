package basic.linkedhashsetcustom;

/**
 * Created by siege on 2017/8/16.
 */
public class LinkedHashSetCustomApp {
    public static void main(String[] args) {
        LinkedHashSetCustom<Integer> linkedHashSetCustom = new LinkedHashSetCustom<Integer>();
        linkedHashSetCustom.add(21);
        linkedHashSetCustom.add(25);
        linkedHashSetCustom.add(30);
        linkedHashSetCustom.add(33);
        linkedHashSetCustom.add(35);

        System.out.println("LinkedHashSetCustom contains 21 ="+linkedHashSetCustom.contains(21));
        System.out.println("LinkedHashSetCustom contains 51 ="+linkedHashSetCustom.contains(51));

        System.out.print("Displaying LinkedHashSetCustom: ");
        linkedHashSetCustom.display();

        System.out.println("\n\n21 removed: "+linkedHashSetCustom.remove(21));
        System.out.println("22 removed: "+linkedHashSetCustom.remove(22));

        System.out.print("Displaying LinkedHashSetCustom: ");
        linkedHashSetCustom.display();

    }
}
