package com.zqh.jdk.demoexe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.bt_one);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });

        //jdk
        button.setOnClickListener(v ->  Toast.makeText(MainActivity.this,"点击了jdk1.8",Toast.LENGTH_SHORT).show());
        //forEach()
        List<String> name = Arrays.asList("liujie","huangrui","jiguangpu","zhangsan","wangwu");
//        name.forEach();

    }

    public void start(View view) {
        //jdk1.8之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("zqh","Hello");
            }
        }).start();

        //jdk1.8之后 1.保存参数列表；2.-> 3.方法体
        new Thread(() -> Log.d("zqh","Hello Lambda")).start();
        new Thread(() -> {
            Log.d("zqh","Hello Lambda");
        }).start();

    }
}
