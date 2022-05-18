package com.example.meteo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Diagram extends AppCompatActivity {

    String value ;
    String longitude;
    String latitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagramme);
        Intent intent = getIntent();
        value = intent.getStringExtra("key");
        longitude =  intent.getStringExtra("longitude");
        latitude = intent.getStringExtra("latitude");

/*      GetData Mydata = new GetData();
        ArrayList<ArrayList<String> > lista = Mydata.getprevision();*/
        GraphView graph = (GraphView) findViewById(R.id.graphview);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(0, 14),
                new DataPoint(1, 17),
                new DataPoint(2, 23),
                new DataPoint(3, 26),
                new DataPoint(4, 23),
                new DataPoint(5, 20),
                new DataPoint(6, 15),
                new DataPoint(7, 14),
                new DataPoint(8, 16),
                new DataPoint(9, 12),
                new DataPoint(10, 14),
                new DataPoint(11, 17),
                new DataPoint(12, 23),
                new DataPoint(13, 26),
                new DataPoint(14, 23),
                new DataPoint(15, 27),
                new DataPoint(16, 28),
                new DataPoint(17, 28),
                new DataPoint(18, 27),
                new DataPoint(19, 24),

        });
        graph.addSeries(series);


       /* PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        }); hadiiiiiiiii dyal les points*/



    }
}