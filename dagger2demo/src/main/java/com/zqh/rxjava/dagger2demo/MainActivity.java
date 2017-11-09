package com.zqh.rxjava.dagger2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zqh.rxjava.dagger2demo.bean.Person;
import com.zqh.rxjava.dagger2demo.module.MainModule;
import com.zqh.rxjava.dagger2demo.port.DaggerMainComponent;
import com.zqh.rxjava.dagger2demo.port.MainComponent;
import com.zqh.rxjava.dagger2demo.qualifier.PersonWithContext;
import com.zqh.rxjava.dagger2demo.qualifier.PersonWithName;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class MainActivity extends AppCompatActivity {

    //    @Named("mContext")
    @PersonWithContext
    @Inject
    Person person;


    //    @Named("String")
    @PersonWithName
    @Inject
    Person person2;

    @Inject
    Lazy<Person> lazyPerson;//懒加载--多次获取的是同一对象

    @Inject
    Provider<Person> providerPerson;//强制重新加载--每次都会尝试创建新的对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainComponent component = DaggerMainComponent.builder()
                .mainModule(new MainModule(this)).build();
        component.inject(this);

    }

}
