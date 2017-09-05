package com.zqh.jdk.port;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by zqh on 2017/9/5.
 */

public interface Formula {

    double caculate(int a);
    @TargetApi(Build.VERSION_CODES.N)
    default double sqrt(int b){
        return Math.sqrt(b);
    }
}
