package com.zqh.jdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.zqh.jdk.port.Formula;
import com.zqh.jdk.utils.TestMethodRefrence;

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


        //1.0 接口默认方法
        Formula formula = new Formula() {
            @Override
            public double caculate(int a) {
                return sqrt(a*100);
            }
        };

        double caculate = formula.caculate(2);
        System.out.println("caculate = "+caculate);
        double sqrt = formula.sqrt(3);

    }

}
