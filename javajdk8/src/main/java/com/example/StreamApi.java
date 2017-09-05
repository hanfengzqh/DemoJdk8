package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zqh on 2017/9/3.
 */

public class StreamApi {


    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 1, null, null, 5, 6, 7, 8, 9, 10,2,3,4);
        int sum = nums.stream()
                .filter(item -> item != null)//filter:操作符过滤作用(输入满足的条件)
                .distinct()//distinct:操作符：去重
                .mapToInt(item -> item * 2)//mapToInt:操作符：对集合中的每一个元素进行整形运算
                .skip(2)//skip操作符：跳过两个元素
                .limit(4)//limit操作符：返回指定元素个数
                .sum();//sum:属于聚合函数，同时也是结束函数(此时才进行循环)
        nums.forEach(System.out::println);
        System.out.println("sum: " + sum);
    }

}
