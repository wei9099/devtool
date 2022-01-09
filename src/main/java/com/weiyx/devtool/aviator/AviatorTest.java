package com.weiyx.devtool.aviator;

import com.googlecode.aviator.AviatorEvaluator;

public class AviatorTest {
    public static void main(String[] args) {
        Object execute = AviatorEvaluator.execute("1+1+2");
        System.out.println(execute.toString() + execute.getClass().getSimpleName());
    }
}
