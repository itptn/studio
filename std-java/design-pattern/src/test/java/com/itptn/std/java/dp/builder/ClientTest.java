package com.itptn.std.java.dp.builder;

import org.junit.Test;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/11 11:24 AM
 */
public class ClientTest {

    @Test
    public void testBuilderPattern() {
        ConcreteBuilder b1 = new ConcreteBuilder();//建造者

        Director director = new Director(b1);//监工
        director.construct();//建造实例(监工负责监督，建造者实际建造)
        String result = b1.getResult();//获取最终生成结果

        System.out.printf("The result is :%n%s", result);
    }
}
