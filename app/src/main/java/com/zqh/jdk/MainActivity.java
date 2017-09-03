package com.zqh.jdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.bt_button);
        button.setOnClickListener((v) -> {
            System.out.println("onclick--jdk 8 新特性");
            Toast.makeText(getApplicationContext(),"点击",Toast.LENGTH_SHORT).show();
        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
