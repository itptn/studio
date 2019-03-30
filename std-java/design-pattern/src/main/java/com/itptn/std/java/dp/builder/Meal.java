package com.itptn.std.java.dp.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/9 7:34 PM
 */
public class Meal {

    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.println(item);
        }
    }
}