package com.example;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass {
    public static void main(String[] args) {

        System.out.println("jdk8...");
        //jdk8之前
        testThread();
        //jdk 8之后
        testThreadLambda();
        //lambda 方法引用::
        testMethodLambda();
        //lambda 集合内部循环
        testListForEach();

        //2.0 lambda表达式
        List<String> name = Arrays.asList("liujie","huangrui","jiguangpu","zhangsan","wangwu");

        Collections.sort(name,(String a, String b) -> {return b.compareTo(a);});
        Collections.sort(name,(a,b) -> {return b.compareTo(a);});
        Collections.sort(name,(a,b) -> b.compareTo(a));

        //老版本
        Collections.sort(name, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return t1.compareTo(s);
            }
        });


        //操作符--升序
        name.stream()
                .map(a -> a.toUpperCase())
                .sorted((a,b) -> a.compareTo(b))
                .forEach(System.out::println);


        //操作符--降序
        name.stream()
                .map(a -> a.toUpperCase())
                .sorted((a,b) -> b.compareTo(a))
                .forEach(System.out::println);

        Map<String,String> map = new HashMap<>();
        map.putIfAbsent("","");

        //Clock时钟
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();
        java.util.Date from = Date.from(instant);

    }

    /**
     * lambda 集合内部循环
     */
    private static void testListForEach() {
        List<Persion> list = new ArrayList<>();
        //jdk 5.0之前
        for (int i = 0; i < 5; i++) {
            Persion p = new Persion("persion" + i, i);
            list.add(p);
        }
        //jdk 5.0之后
        for (Persion item : list) {
            item.setAge(18);
        }

        for (Persion item : list) {
            System.out.println(item);
        }
        System.out.println("-----------------------------------");
        //jdk 8 lambda 内部循环
        list.forEach(item -> item.setAge(20));
        list.forEach(item -> System.out.println(item));
        System.out.println("-----------------------------------");
        list.forEach(item -> item.setAge(30));
        list.forEach(System.out::println);
    }

    private static void testMethodLambda() {
        //lambda 调用static方法 Object::method
        new Thread(Md5Utils::getStaticFilds).start();
        //lambda 调用filds obj::method
        Md5Utils utils = new Md5Utils();
        new Thread(utils::getFilds).start();
        new Thread(Md5Utils::new).start();
    }

    /**
     * jdk8之后 匿名函数(只保留参数列表 -> 引用方法体)
     */
    private static void testThreadLambda() {
        //只保留参数列表->引用方法体
        new Thread(() -> System.out.println("current thread name:subThread jdk 8")
        ).start();
    }

    /**
     * jdk8之前的函数打印测试
     */
    private static void testThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("current thread name:subThread");

            }
        }).start();
    }
}
