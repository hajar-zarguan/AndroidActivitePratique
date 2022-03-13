package com.example.constraintlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn = (Button) findViewById(R.id.button2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        btn.setText("Texte modifier");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}