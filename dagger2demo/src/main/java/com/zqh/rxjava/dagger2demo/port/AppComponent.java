package com.zqh.rxjava.dagger2demo.port;

import android.content.Context;

import com.zqh.rxjava.dagger2demo.module.AppModule;
import com.zqh.rxjava.dagger2demo.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by zqh on 2017/11/9.
 */
@ApplicationScope
@Component(modules = AppModule.class)
public interface AppComponent {
    //向下层提供Context
    Context getContext();
}
