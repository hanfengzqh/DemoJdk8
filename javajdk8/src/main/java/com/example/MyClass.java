package com.example;

import java.util.ArrayList;
import java.util.List;

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
