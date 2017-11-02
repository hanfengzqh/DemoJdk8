package com.zqh.rxjava.realmapp.dbmanager;

import android.text.TextUtils;

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

    public static DbManager get() {
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

    //Realm添加数据---所有操作都必须放在事物之内
    public void addData() {
        mRealm.beginTransaction();
        for (int i = 0; i < 10; i++) {
            User user = mRealm.createObject(User.class, UUID.randomUUID().toString());
            user.setAge(10 + i);
//            user.setId(UUID.randomUUID().toString());
            user.setName("user" + i);
            mRealm.insert(user);
        }
        mRealm.commitTransaction();
    }

    //Realm数据查询---所有操作都必须放在事物之内
    public void queryData() {
        mRealm.beginTransaction();
        RealmResults<User> users = mRealm.where(User.class).findAll();
        for (User user : users
                ) {
            Logger.d("user = " + user.getId() + ": " + user.getName() + ": " + user.getAge());
        }
        mRealm.commitTransaction();
    }

    //查询所有数据---所有操作都必须放在事物之内
    public RealmResults<User> queryDataAll() {
        mRealm.beginTransaction();
        RealmResults<User> users = mRealm.where(User.class).findAll();
        for (User user : users
                ) {
            Logger.d("user = " + user.getId() + ": " + user.getName() + ": " + user.getAge());
        }
        mRealm.commitTransaction();
        return users;
    }

    //Realm数据修改---所有操作都必须放在事物之内
    public void updateById(String id, User mUser) {
        mRealm.beginTransaction();
        //查询
        User user = mRealm.where(User.class).equalTo("id", id).findFirst();

        //更新字段
        user.setName(mUser.getName());
        user.setAge(mUser.getAge());

        //更新到realm中
//        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(user);
        mRealm.commitTransaction();
    }

    //删除
    public void delete(User mUser) {
        if (TextUtils.isEmpty(mUser.getId())) {
            throw new IllegalArgumentException("非法参数: Movie的id不正确");
        }

        if (mUser.isValid()) {//如果对象仍然可访问或非托管对象{@code false}否则。
            mRealm.beginTransaction();
            mUser.deleteFromRealm();
            mRealm.commitTransaction();
            return;
        }

        deleteById(mUser.getId());

    }

    private void deleteById(String id) {
        mRealm.beginTransaction();
        User user = mRealm.where(User.class).equalTo("id", id).findFirst();
        //删除
        user.deleteFromRealm();
        mRealm.commitTransaction();
    }
}
