package com.zqh.rxjava.dagger2demo.module;

import android.content.Context;

import com.zqh.rxjava.dagger2demo.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zqh on 2017/11/9.
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @ApplicationScope
    @Provides
    public Context providesContext() {
        return mContext;
    }
}
