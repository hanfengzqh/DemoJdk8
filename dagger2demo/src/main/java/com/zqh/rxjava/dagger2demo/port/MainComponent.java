package com.zqh.rxjava.dagger2demo.port;

import com.zqh.rxjava.dagger2demo.MainActivity;
import com.zqh.rxjava.dagger2demo.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zqh on 2017/11/9.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
