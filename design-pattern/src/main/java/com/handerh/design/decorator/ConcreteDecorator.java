package com.handerh.design.decorator;

/**
 * @author handerh
 * @date 2021/01/09
 * 具体装饰类
 */
public class ConcreteDecorator extends Decorator{

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    void operation() {
        decoratorOperation();
        super.operation();
    }

    private void decoratorOperation(){
        System.out.println("this is decoratorOperation");
    }

}
