package com.example.mysql_androidstudio_jdbc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =  findViewById(R.id.textv);
        GetData Mydata = new GetData();
        String g = Mydata.getdata();
        textView.setText(g);

    }
}