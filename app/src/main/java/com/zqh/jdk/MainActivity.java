package com.zqh.jdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.zqh.jdk.utils.TestMethodRefrence;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.bt_button);
        Button button1 = (Button) findViewById(R.id.bt_button2);

        button.setOnClickListener((v) -> {
            System.out.println("onclick--jdk 8 新特性");
            Toast.makeText(getApplicationContext(), "点击", Toast.LENGTH_SHORT).show();
        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TestMethodRefrence.doSomething(view,MainActivity.this);
//            }
//        });

        button1.setOnClickListener((v)-> TestMethodRefrence.doSomething(v,MainActivity.this));

//        button1.setOnClickListener(TestMethodRefrence::doSomething2);


        //1.0 接口默认方法需要的最低执行版本为API24
        /*Formula formula = new Formula() {
            @Override
            public double caculate(int a) {
                return sqrt(a*100);
            }
        };

        double caculate = formula.caculate(2);
        System.out.println("caculate = "+caculate);
        double sqrt = formula.sqrt(3);*/

        //2.0 lambda表达式
        List<String> name = Arrays.asList("liujie","huangrui","jiguangpu","zhangsan","wangwu");

        Collections.sort(name,(String a,String b) -> {return b.compareTo(a);});
        Collections.sort(name,(a,b) -> {return b.compareTo(a);});
        Collections.sort(name,(a,b) -> b.compareTo(a));

        //老版本
        Collections.sort(name, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return t1.compareTo(s);
            }
        });


        //操作符
        name.stream()
                .map(a -> a.toUpperCase())
                .sorted((a,b) -> b.compareTo(b))
                .forEach(System.out::println);

    }

}
