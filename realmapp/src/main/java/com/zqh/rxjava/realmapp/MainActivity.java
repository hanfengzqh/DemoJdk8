package com.zqh.rxjava.realmapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.zqh.rxjava.commutilslibrary.dialogutil.ToastUtil;
import com.zqh.rxjava.realmapp.base.BaseActivity;
import com.zqh.rxjava.realmapp.dbmanager.DbManager;
import com.zqh.rxjava.realmapp.dbmanager.Migration;
import com.zqh.rxjava.realmapp.infor.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bt_creatdb)
    Button bt_creatdb;//数据库创建
    @BindView(R.id.bt_insertdb)
    Button bt_insertdb;//插入
    @BindView(R.id.bt_deletedb)
    Button bt_deletedb;//删除
    @BindView(R.id.bt_modifydb)
    Button bt_modifydb;//修改
    @BindView(R.id.bt_querydb)
    Button bt_querydb;//查询
    @BindView(R.id.bt_query_byid)
    Button bt_query_byid;//查询指定数据
    @BindView(R.id.bt_query_bysort)
    Button bt_query_bysort;//查询数据排序
    @BindView(R.id.bt_query_sync_add)
    Button bt_query_sync_add;//异步增加
    @BindView(R.id.bt_query_sync_delete)
    Button bt_query_sync_delete;//异步删除
    @BindView(R.id.bt_query_sync)
    Button bt_query_sync;//异步查询
    @BindView(R.id.bt_db_update)
    Button bt_db_update;//数据库更新
    @BindView(R.id.bt_query_other)
    Button bt_query_other;//其他查询

    private Context mContext;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        setListener();
    }

    private void setListener() {
        bt_creatdb.setOnClickListener(this);
        bt_insertdb.setOnClickListener(this);
        bt_deletedb.setOnClickListener(this);
        bt_modifydb.setOnClickListener(this);
        bt_querydb.setOnClickListener(this);
        bt_query_byid.setOnClickListener(this);
        bt_query_bysort.setOnClickListener(this);
        bt_query_other.setOnClickListener(this);
        bt_query_sync_add.setOnClickListener(this);
        bt_query_sync_delete.setOnClickListener(this);
        bt_query_sync.setOnClickListener(this);
        bt_db_update.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_creatdb: {
                ToastUtil.showShortToast(mContext, "数据库创建");
                Logger.d("数据库创建");
                break;
            }
            case R.id.bt_insertdb: {
                ToastUtil.showShortToast(mContext, "插入数据");
                Logger.d("插入数据");
                DbManager.get().addData();
                break;
            }
            case R.id.bt_deletedb: {
                ToastUtil.showShortToast(mContext, "删除数据");
                Logger.d("删除数据");
                RealmResults<User> users = DbManager.get().queryDataAll();
                for (User mUser : users) {
                    if (mUser.getAge() < 36) {
                        DbManager.get().delete(mUser);
                    }
                }
                break;
            }
            case R.id.bt_modifydb: {
                ToastUtil.showShortToast(mContext, "修改数据");
                Logger.d("修改数据");
                RealmResults<User> users = DbManager.get().queryDataAll();
                for (User mUser : users) {
                    User user = new User();
                    user.setName("USER" + mUser.getName());
                    user.setAge(20 + mUser.getAge());
                    user.setFullName("shhshshhs");
                    DbManager.get().updateById(mUser.getId(), user);
                }
                break;
            }
            case R.id.bt_querydb: {
                ToastUtil.showShortToast(mContext, "数据查询");
                Logger.d("数据查询");
                DbManager.get().queryData();
                break;
            }
            case R.id.bt_query_byid: {
                ToastUtil.showShortToast(mContext, "查询指定数据");
                Logger.d("查询指定数据");
                RealmResults<User> users = DbManager.get().queryDataAll();
                for (User mUser : users) {
                    if (mUser.getAge() == 38) {
                        DbManager.get().queryById(mUser.getId(), mUser);
                    }
                }
                break;
            }
            case R.id.bt_query_bysort: {
                ToastUtil.showShortToast(mContext, "查询数据排序");
                Logger.d("查询数据排序");
                DbManager.get().queryDataSort();
                break;
            }
            case R.id.bt_query_other:
                double averageAge = DbManager.get().getAverageAge();
                int sumAge = DbManager.get().getSumAge();
                int maxAge = DbManager.get().getMaxAge();
                Logger.d("averageAge = " + averageAge + " : sumAge = " + sumAge + " : maxAge = " + maxAge);
                break;
            case R.id.bt_query_sync_add://异步增加
                DbManager.get().syncAddUser(this);
                break;
            case R.id.bt_query_sync_delete://异步删除
                RealmResults<User> users = DbManager.get().queryDataAll();
                for (User mUser : users) {
                    if (mUser.getAge() < 58) {
                        DbManager.get().syncdeleteUser(this, mUser.getId());
                    }
                }

                break;
            case R.id.bt_query_sync://异步查询
                DbManager.get().syncQuery();
                break;
            case R.id.bt_db_update://数据库版本更新
                Migration mMigration = new Migration();
                DbManager.get().init(1,mMigration);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DbManager.get().destory();
    }

}
