package jvmlearning.annotation;

@CheckGetter
public class Foo {//Type Element
    int a;//VariableElement
    static int b;//VariableElement
    Foo(){}//ExecutableElement
    void setA(//ExecutableElement
            int newA  //VariableElement
    ){}

}
