package com.zqh.rxjava.realmapp.dbmanager;

import com.orhanobut.logger.Logger;
import com.zqh.rxjava.realmapp.infor.User;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by zqh on 2017/10/31.
 */

public final class DbManager {
    private Realm mRealm;

    private DbManager() {

    }

    public static class InstanceHolder {
        private static final DbManager INSTANCE = new DbManager();
    }

    public static DbManager get(){
        return InstanceHolder.INSTANCE;
    }
    /**
     * 初始化Realm, 建议在application的onCreate()方法中调用
     *
     * @param dbVersion
     */
    public void init(int dbVersion) {

        //通过配置config进行初始化
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myRealm.realm")//文件名
                .encryptionKey(new byte[64])//base64加密key
                .schemaVersion(dbVersion)//版本号
                .build();
        mRealm = Realm.getInstance(config);
    }

    /**
     * 退出应用的时候调用
     */
    public void destory() {
        if (mRealm != null) {
            if (!mRealm.isClosed()) {
                mRealm.close();
            }
            mRealm = null;
        }
    }


    public void addData(){
        mRealm.beginTransaction();
        for (int i=0;i<10;i++){
            User user = mRealm.createObject(User.class,UUID.randomUUID().toString());
            user.setAge(10+i);
//            user.setId(UUID.randomUUID().toString());
            user.setName("user"+i);
            mRealm.insert(user);
        }
        mRealm.commitTransaction();
    }

    //Realm数据查询
    public void queryData() {
        mRealm.beginTransaction();
        RealmResults<User> users = mRealm.where(User.class).findAll();
        for (User user: users
                ) {
            Logger.d("user = "+user.getId()+": "+user.getName()+": "+user.getAge());
        }
        mRealm.commitTransaction();
    }
}
