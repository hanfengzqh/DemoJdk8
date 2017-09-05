package com.zqh.jdk.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by zqh on 2017/9/5.
 */

public class TestMethodRefrence {

    public static void doSomething(View view,Context mContext){

        Toast.makeText(mContext,"这里是方法引用实现的方式",Toast.LENGTH_SHORT).show();
        System.out.println("这里是方法引用实现的方式");
    }

    public static void doSomething2(View view){

//        Toast.makeText(mContext,"这里是方法引用实现的方式",Toast.LENGTH_SHORT).show();
        System.out.println("这里是方法引用实现的方式");
    }

}
