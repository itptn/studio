package com.itptn.std.java.dp.builder;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/9 7:24 PM
 */
public abstract class ColdDrink implements Item{

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public String toString() {
        return "Item:ColdDrink[{" +
                "name=" + name() +
                ",packing=" + packing().pack() +
                ",price=" + price() +
                "}]";
    }
}