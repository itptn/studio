package com.itptn.std.java.dp.builder;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/9 7:24 PM
 */
public abstract class Burger implements Item{

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public String toString() {
        return "Item:Burger[{" +
                "name=" + name() +
                ",packing=" + packing().pack() +
                ",price=" + price() +
                "}]";
    }
}
