package com.itptn.std.java.dp.absfactory;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/4 6:08 PM
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){

        if("SHAPE".equalsIgnoreCase(choice)){
            return new ShapeFactory();
        } else if("COLOR".equalsIgnoreCase(choice)){
            return new ColorFactory();
        }
        return null;
    }
}
