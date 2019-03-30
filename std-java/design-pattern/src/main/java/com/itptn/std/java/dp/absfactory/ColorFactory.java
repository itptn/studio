package com.itptn.std.java.dp.absfactory;

import com.itptn.std.java.dp.*;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/4 6:06 PM
 */
public class ColorFactory extends AbstractFactory{

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
