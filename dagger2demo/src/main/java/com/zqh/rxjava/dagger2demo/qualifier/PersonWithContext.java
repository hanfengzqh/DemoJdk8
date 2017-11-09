package com.zqh.rxjava.dagger2demo.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by zqh on 2017/11/9.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonWithContext {
}
