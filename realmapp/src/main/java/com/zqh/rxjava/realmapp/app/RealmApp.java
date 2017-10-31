package com.zqh.rxjava.realmapp.app;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.zqh.rxjava.realmapp.dbmanager.DbManager;

import io.realm.Realm;

/**
 * Created by zqh on 2017/10/19.
 */

public class RealmApp extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this.getApplicationContext();
        init();
    }

    private void init() {
        //Realm默认初始化
        Realm.init(this);
        DbManager.get().init(0);
//        Realm mRealm = Realm.getDefaultInstance();
        Stetho.initialize(//Stetho初始化
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build()
        );
        /**初始化Logger*/
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(0)         // （可选）要显示的方法行数。 默认2
                .methodOffset(5)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("zqh")   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    /**
     * 获取全局的context
     */
    public static Context getappContext() {
        return appContext;
    }

}
