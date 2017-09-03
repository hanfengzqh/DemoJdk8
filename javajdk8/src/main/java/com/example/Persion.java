package com.example;

/**
 * Created by zqh on 2017/9/3.
 */

public class Persion {

    private String name;
    private int age;

    public Persion(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
