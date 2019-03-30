package com.itptn.std.java.dp.builder;

import org.junit.Test;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/9 7:35 PM
 */
public class BuilderPatternTest {

    @Test
    public void builderPatternTest(){
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }
}
