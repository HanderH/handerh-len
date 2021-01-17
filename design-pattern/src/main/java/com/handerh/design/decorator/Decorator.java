package com.handerh.design.decorator;

/**
 * @author handerh
 * @date 2021/01/09
 * 抽象装饰类
 */
public  abstract  class Decorator extends Component {

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    void operation() {
        component.operation();
    }
}

