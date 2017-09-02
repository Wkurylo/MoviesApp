package com.example.wojtekkurylo.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    ArrayAdapter<String> mFlavoursAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MovieComponent[] movieComponents =
//                {
//                        new MovieComponent("title 1","release date 1", 111, "1.0", "plot 1"),
//                        new MovieComponent("title 2","release date 2", 222, "2.0", "plot 2"),
//                        new MovieComponent("title 3","release date 3", 333, "3.0", "plot 3"),
//                        new MovieComponent("title 4","release date 4", 444, "4.0", "plot 4"),
//                        new MovieComponent("title 5","release date 5", 555, "5.0", "plot 5"),
//                        new MovieComponent("title 6","release date 6", 666, "6.0", "plot 6"),
//                };
//        String[] data = {
//                "Cupcake 1.5",
//                "Donut 1.6",
//                "Eclair", "2.0-2.1",
//                "Froyo", "2.2-2.2.3",
//                "GingerBread", "2.3-2.3.7",
//                "Honeycomb", "3.0-3.2.6",
//                "Ice Cream Sandwich", "4.0-4.0.4",
//                "Jelly Bean", "4.1-4.3.1",
//                "KitKat", "4.4-4.4.4",
//                "Lollipop", "5.0-5.1.1"
//        };
//
//        List<String> flavours = new ArrayList<String>(Arrays.asList(data));
//
//        mFlavoursAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.recyclerview_item_row, data);
//        GridView gridView = (GridView) findViewById(R.id.gridview);
//        gridView.setAdapter(mFlavoursAdapter);
    }
}
