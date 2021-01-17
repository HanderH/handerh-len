package com.handerh.design.decorator;

/**
 * @author handerh
 * @date 2021/01/09
 */
public class Test {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();

        ConcreteDecorator concreteDecorator = new ConcreteDecorator(component);

        component.operation();

        System.out.println("------------华丽丽的分割线---------------------");

        concreteDecorator.operation();
    }
}
