package spoon;

import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtRHSReceiver;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtEnumValue;
import spoon.reflect.factory.Factory;

public class ClassTransformation {
    public static void main(String[] args) {
        // Create a Spoon Launcher
        Launcher launcher = new Launcher();
        // Add the input resource - your enum Java file
        launcher.addInputResource("src/main/java/spoon/test/BizLockEnum.java");
        // Set the output directory for the modified code
        launcher.setSourceOutputDirectory("src/main/java");
        // Build the model
        launcher.buildModel();
        // Get the factory
        Factory factory = launcher.getFactory();
        // Retrieve the CtEnum representation of the BizLockEnum class
        CtEnum<?> bizLockEnum = (CtEnum) factory.Class().get("spoon.test.BizLockEnum");
        CtEnumValue<?> addCity = bizLockEnum.getEnumValue("ADD_CITY");
        if (addCity != null) return;
        // Create the new enum constant ADD_CITY
        CtEnumValue<?> addCityEnumValue = factory.createEnumValue();
        addCityEnumValue.setSimpleName("ADD_CITY");
        CtComment comment = factory.createComment("添加城市", CtComment.CommentType.BLOCK);
        addCityEnumValue.addComment(comment);
        // Create constructor arguments (code and desc)
        CtExpression<String> codeArgument = factory.Code().createLiteral("ADD_CITY");
        CtExpression<String> descArgument = factory.Code().createLiteral("添加城市");
        // Create a constructor call for the enum constant
        CtConstructorCall<?> constructorCall = factory.Code().createConstructorCall(bizLockEnum.getReference());
        // Add the arguments to the constructor call
        constructorCall.addArgument(codeArgument);
        constructorCall.addArgument(descArgument);
        // Assign the constructor call to the enum value
        ((CtRHSReceiver) addCityEnumValue).setAssignment(constructorCall);
        // Add the new enum constant to the enum
        bizLockEnum.addEnumValue(addCityEnumValue);
        // Save the changes
        launcher.prettyprint();
    }
}
