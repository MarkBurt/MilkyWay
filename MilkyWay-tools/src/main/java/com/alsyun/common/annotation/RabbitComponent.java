package com.alsyun.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Markburt
 * 消息队列初始化注解
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RabbitComponent {


    /***
     *  @AliasFor 表示别名，它可以注解到自定义注解的两个属性上，表示这两个互为别名，也就是说这两个属性其实同一个含义。
     *
     * 自定义注解有一个属性，且该属性命名上为了体现其含义，所以有些复杂，这样调用方必须每次使用自定义注解的时候，都必须写明 属性 ，然后设置，这样会比较负责；
     * 同时若自定义注解继承了另一个注解，要想让调用方能够设置继承过来的属性值，就必须在自定义注解中重新定义一个属性，同时声明该属性是父注解某个属性的别名。
     * @return
     */
    @AliasFor(
            annotation = Component.class
    )
    String value();
}
