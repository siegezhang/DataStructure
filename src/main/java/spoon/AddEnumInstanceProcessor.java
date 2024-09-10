package spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;

public class AddEnumInstanceProcessor extends AbstractProcessor<CtType<?>> {

    @Override
    public boolean isToBeProcessed(CtType<?> element) {
        return true;
    }

    @Override
    public void process(CtType<?> type) {
        Factory factory = getFactory();
        CtEnum<?> enumType = (CtEnum<?>) type;

        // Create the new enum constant
        CtEnumValue<?> ctEnum = factory.Core().createEnumValue();
        ctEnum.setSimpleName("ADD_CITY");

        // Create constructor call with arguments
        CtConstructorCall<?> constructorCall = factory.Core().createConstructorCall();
        //constructorCall.setType(enumType);
        constructorCall.addArgument(factory.createLiteral("ADD_CITY"));
        constructorCall.addArgument(factory.createLiteral("添加城市"));
       // enumType.setConstructors(constructorCall);

        // Add the new constant to the enum
 //       enumType.addEnumValue(newConstant);
    }
}