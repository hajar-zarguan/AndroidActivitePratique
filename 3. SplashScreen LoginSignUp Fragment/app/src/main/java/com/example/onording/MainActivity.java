package com.example.onording;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onording.HelperClasses.SliderAdapter;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager2 ;
    LinearLayout dotsLayout ;
    Button button;
    SliderAdapter sliderAdapter ;
    TextView[] dots ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Create",Toast.LENGTH_LONG).show();
        viewPager2 = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        button = findViewById(R.id.get_started);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        //call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager2.setAdapter(sliderAdapter);
        addDots(0);
        viewPager2.addOnPageChangeListener(changeListener); }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"Start",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"Resume",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"Restart",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"Pause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"Stop",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Destroy",Toast.LENGTH_LONG).show();
    }
    private  void addDots(int position){
    dots = new  TextView[4];
    dotsLayout.removeAllViews();
    for (int i = 0 ; i<dots.length ; i++){
        dots[i] = new TextView(this);
        dots[i].setText(Html.fromHtml("&#8226;"));
        dots[i].setTextSize(70);
        dotsLayout.addView(dots[i]);

    }
    if (dots.length>0){
        dots[position].setTextColor(getResources().getColor(R.color.purple_500));
    }


    }



    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
        addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}