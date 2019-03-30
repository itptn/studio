package com.itptn.std.java.dp.absfactory;

import com.itptn.std.java.dp.Color;
import com.itptn.std.java.dp.Shape;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/4 6:04 PM
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}
