package com.itptn.std.java.dp.builder;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/9 7:27 PM
 */
public class VegBurger extends Burger{

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.5F;
    }
}
