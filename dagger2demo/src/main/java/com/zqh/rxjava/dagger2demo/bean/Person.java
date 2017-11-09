package com.zqh.rxjava.dagger2demo.bean;

import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * Created by zqh on 2017/11/9.
 */

public class Person {
    private Context context;
    private String name;

    public Person(Context mContext) {
        Logger.d("a person created.");
    }

    public Person(String name) {
        this.name = name;
    }
}
