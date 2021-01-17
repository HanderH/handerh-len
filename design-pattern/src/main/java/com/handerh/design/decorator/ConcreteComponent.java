package com.handerh.design.decorator;

/**
 * @author handerh
 * @date 2021/01/09
 * @desc 具体构件
 */
public class ConcreteComponent extends Component{

    void operation() {
        System.out.println("is a operation,no decorator");
    }
}
