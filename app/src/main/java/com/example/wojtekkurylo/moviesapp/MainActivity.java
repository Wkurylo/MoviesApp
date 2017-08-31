package com.example.wojtekkurylo.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> mFlavoursAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] data = {
                "Cupcake 1.5",
                "Donut 1.6",
                "Eclair", "2.0-2.1",
                "Froyo", "2.2-2.2.3",
                "GingerBread", "2.3-2.3.7",
                "Honeycomb", "3.0-3.2.6",
                "Ice Cream Sandwich", "4.0-4.0.4",
                "Jelly Bean", "4.1-4.3.1",
                "KitKat", "4.4-4.4.4",
                "Lollipop", "5.0-5.1.1"
        };

        List<String> flavours = new ArrayList<String>(Arrays.asList(data));

        mFlavoursAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.single_grid_view,data);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mFlavoursAdapter);
    }
}
