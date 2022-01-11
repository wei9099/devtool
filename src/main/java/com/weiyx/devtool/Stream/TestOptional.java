package com.weiyx.devtool.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestOptional {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        Optional<String> first = list.stream().filter(s -> s.length() > 2).findFirst();

//        String s = first.orElseThrow(RuntimeException::new);
        String s = first.orElse("not found");
        // 正确的用法是放在返回值
        System.out.println(s);
    }
}
