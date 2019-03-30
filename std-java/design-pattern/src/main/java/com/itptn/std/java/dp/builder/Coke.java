package com.itptn.std.java.dp.builder;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/9 7:32 PM
 */
public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 10.5F;
    }
}
