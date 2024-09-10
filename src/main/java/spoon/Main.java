package spoon;

public class Main {
    public static void main(String[] args) {
        SpoonAPI spoon = new Launcher();
        spoon.addInputResource("src/main/java/spoon/test"); // Replace with your source code path
        spoon.setSourceOutputDirectory("src/main/java/");
      //  spoon.addProcessor(new AddFieldProcessor());
        spoon.addProcessor(new AddEnumInstanceProcessor());
        spoon.buildModel();
        spoon.process();
        spoon.prettyprint();
    }
}
