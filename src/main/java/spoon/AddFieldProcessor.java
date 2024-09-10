package spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;

public class AddFieldProcessor extends AbstractProcessor<CtType<?>> {

    @Override
    public boolean isToBeProcessed(CtType<?> element) {
        return true;
    }

    @Override
    public void process(CtType<?> type) {
        Factory factory = getFactory();
        CtField<String> newField = factory.Core().createField();
        newField.setType(factory.Type().stringType());
        newField.setSimpleName("newField");
        newField.setDefaultExpression(factory.createLiteral("initial value")); // Optional default value
        ((CtClass<?>) type).addField(newField);
    }
}