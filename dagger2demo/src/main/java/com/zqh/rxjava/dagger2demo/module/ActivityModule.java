package com.zqh.rxjava.dagger2demo.module;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zqh.rxjava.dagger2demo.bean.Person;
import com.zqh.rxjava.dagger2demo.qualifier.PersonWithContext;
import com.zqh.rxjava.dagger2demo.qualifier.PersonWithName;
import com.zqh.rxjava.dagger2demo.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zqh on 2017/11/9.
 */

@Module
public class ActivityModule {

    @ActivityScope
    @Provides
    Person providesPerson(Context mContext) {
        return new Person(mContext);
    }

    @ActivityScope
    @PersonWithContext
//    @Singleton
    @Provides
    Person providesPersonWithContext(Context mContext) {
        Logger.d("a person created from MainMoudle");
        return new Person(mContext);
    }

    @ActivityScope
    //    @Named("String")
    @PersonWithName
//    @Singleton
    @Provides
    Person providesPersonWithName() {
        Logger.d("a person created from MainMoudle");
        return new Person("liujie");
    }
}
