package com.weiyx.devtool.Stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestSteam {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a", "b", "a", "c");
        /**
         * 中间操作
         * filter map sorted
         * 终结操作（流只能被消费一次）
         */
        HashMap<String, Integer> countMap = new HashMap<>();
        /**
         * 函数应当没有副作用 可以多次执行也可以不执行 不会对外界产生影响
         * 例如 foreach
         */
        /*
            这就是一个有副作用的函数
            会改变外界状态，即 countMap
         */
        strings.forEach(word -> {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        });
        /*
            推荐写法
         */
        Map<String, Long> collect = strings.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }
}
