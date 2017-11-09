package com.zqh.rxjava.dagger2demo.port;

import com.zqh.rxjava.dagger2demo.MainActivity;
import com.zqh.rxjava.dagger2demo.scope.ActivityScope;

import dagger.Component;

/**
 * Created by zqh on 2017/11/9.
 */

@ActivityScope
@Component(modules = {ActivityComponent.class}, dependencies = {AppComponent.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
