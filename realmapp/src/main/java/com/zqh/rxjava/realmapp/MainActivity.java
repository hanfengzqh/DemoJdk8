package com.zqh.rxjava.realmapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.zqh.rxjava.commutilslibrary.dialogutil.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    private Realm mRealm;
    private Context mContext;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initRealm();
        setListener();


    }

    private void setListener() {
        bt_creatdb.setOnClickListener(this);
        bt_insertdb.setOnClickListener(this);
        bt_deletedb.setOnClickListener(this);
        bt_modifydb.setOnClickListener(this);
        bt_querydb.setOnClickListener(this);
    }

    //初始化Realm数据库
    private void initRealm() {
        //通过配置config进行初始化
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myRealm.realm")//文件名
                .schemaVersion(0)//版本号
                .build();
        mRealm = Realm.getInstance(config);
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
                break;
            }
            case R.id.bt_deletedb: {
                ToastUtil.showShortToast(mContext, "删除数据");
                Logger.d("插入数据");

                break;
            }
            case R.id.bt_modifydb: {
                ToastUtil.showShortToast(mContext, "修改数据");
                Logger.d("修改数据");
                break;
            }
            case R.id.bt_querydb: {
                ToastUtil.showShortToast(mContext, "数据查询");
                Logger.d("数据查询");
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

}
