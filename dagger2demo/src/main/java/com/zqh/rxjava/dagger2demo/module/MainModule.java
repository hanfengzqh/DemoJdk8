package com.zqh.rxjava.dagger2demo.module;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zqh.rxjava.dagger2demo.bean.Person;
import com.zqh.rxjava.dagger2demo.qualifier.PersonWithContext;
import com.zqh.rxjava.dagger2demo.qualifier.PersonWithName;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zqh on 2017/11/9.
 */
@Module
public class MainModule {

    private Context mContext;

    public MainModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public Context providesContext() {
        return this.mContext;
    }

    //    @Named("mContext")
    @PersonWithContext
//    @Singleton
    @Provides
    Person providesPersonWithContext(Context mContext) {
        Logger.d("a person created from MainMoudle");
        return new Person(mContext);
    }

    //    @Named("String")
    @PersonWithName
//    @Singleton
    @Provides
    Person providesPersonWithName() {
        Logger.d("a person created from MainMoudle");
        return new Person("liujie");
    }

}
