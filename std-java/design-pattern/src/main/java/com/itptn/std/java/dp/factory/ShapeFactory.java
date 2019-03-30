package com.itptn.std.java.dp.factory;

import com.itptn.std.java.dp.Circle;
import com.itptn.std.java.dp.Rectangle;
import com.itptn.std.java.dp.Shape;
import com.itptn.std.java.dp.Square;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/3 7:51 PM
 */
public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
