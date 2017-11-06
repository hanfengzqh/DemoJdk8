package com.zqh.rxjava.realmapp.dbmanager;

import android.content.Context;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.zqh.rxjava.commutilslibrary.dialogutil.ToastUtil;
import com.zqh.rxjava.realmapp.infor.User;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by zqh on 2017/10/31.
 */

public final class DbManager {
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTaskAdd, realmAsyncTaskDelete;
    private RealmResults<User> allAsync;
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
     * 初始化Realm, 建议在application的onCreate()方法中调用
     *
     * @param dbVersion
     */
    public void init(int dbVersion, RealmMigration mMigration) {

        //通过配置config进行初始化
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myRealm.realm")//文件名
                .encryptionKey(new byte[64])//base64加密key
                .schemaVersion(dbVersion)//版本号
                .migration(mMigration)
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
        if (realmAsyncTaskAdd != null && !realmAsyncTaskAdd.isCancelled()) {
            realmAsyncTaskAdd.cancel();
        }
        if (realmAsyncTaskDelete != null && !realmAsyncTaskDelete.isCancelled()) {
            realmAsyncTaskDelete.cancel();
        }
        allAsync.removeChangeListeners();
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
            Logger.d("user = " + user.getId() + ": " + user.getName() + ": " + user.getAge()+" : "+user.getFullName());
        }
        mRealm.commitTransaction();
    }

    //查询排序
    public void queryDataSort() {
        mRealm.beginTransaction();
        RealmResults<User> users = mRealm.where(User.class).findAll();
        //增序排列
        // users = users.sort("age");
        //降序排列
        users = users.sort("age", Sort.DESCENDING);
        for (User user : users
                ) {
            Logger.d("user = " + user.getId() + ": " + user.getName() + ": " + user.getAge()+" : "+user.getFullName());
        }
        mRealm.commitTransaction();
    }

    //查询所有数据---所有操作都必须放在事物之内
    public RealmResults<User> queryDataAll() {
        mRealm.beginTransaction();
        RealmResults<User> users = mRealm.where(User.class).findAll();
        for (User user : users
                ) {
            Logger.d("user = " + user.getId() + ": " + user.getName() + ": " + user.getAge()+" : "+user.getFullName());
        }
        mRealm.commitTransaction();
        return users;
    }

    public User queryById(String id, User mUser) {
        mRealm.beginTransaction();
        User user = mRealm.where(User.class).equalTo("id", id).findFirst();
        Logger.e("user = " + user.getId() + ": " + user.getName() + ": " + user.getAge()+" : "+user.getFullName());
        mRealm.commitTransaction();
        return user;
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

    public double getAverageAge() {
        mRealm.beginTransaction();
        //查询平均年龄
        double age = mRealm.where(User.class).findAll().average("age");
        mRealm.commitTransaction();
        return age;
    }


    public int getSumAge() {
        mRealm.beginTransaction();
        //查询年龄总和
        Number age = mRealm.where(User.class).findAll().sum("age");
        int intValue = age.intValue();
        mRealm.commitTransaction();
        return intValue;
    }

    public int getMaxAge() {
        mRealm.beginTransaction();
        //查询最大年龄
        Number age = mRealm.where(User.class).findAll().max("age");
        int intValue = age.intValue();
        mRealm.commitTransaction();
        return intValue;
    }

    public void syncAddUser(final Context mContext) {
        realmAsyncTaskAdd = this.mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class,UUID.randomUUID().toString());
//                user.setId(UUID.randomUUID().toString());
                user.setAge(100);
                user.setName("liujie");
                realm.copyToRealm(user);
//                realm.insert(user);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                ToastUtil.showShortToast(mContext, "添加成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Logger.e(error.toString());
                ToastUtil.showShortToast(mContext, "添加失败");
            }
        });

    }


    public void syncdeleteUser(final Context mContext, final String id) {
        realmAsyncTaskDelete = this.mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User query = realm.where(User.class).equalTo("id", id).findFirst();
                query.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                ToastUtil.showShortToast(mContext, "删除成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Logger.e(error.toString());
                ToastUtil.showShortToast(mContext, "删除失败");
            }
        });
    }

    public void syncQuery(){
        allAsync = mRealm.where(User.class).findAllAsync();
        allAsync.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> element) {
                element = element.sort("age");
                mRealm.copyFromRealm(element);
                for (User user : element){
                    Logger.i("user = " + user.getId() + ": " + user.getName() + ": " + user.getAge()+" : "+user.getFullName());
                }
            }
        });
    }
}
