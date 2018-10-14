package com.example.cas.geoguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<GeoObject> mGeoObjects;
    GeoObjectAdapter mAdapter;

    TextView textView;

    private final static int SWIPE_RIGHT_DIRECTION = 8;
    private final static int SWIPE_LEFT_DIRECTION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGeoObjects = new ArrayList<>();
        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_IN_EUROPE.length; i++) {
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_IN_EUROPE[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
        }

        textView = findViewById(R.id.textView);

        RecyclerView mGeoRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        notifyUser(position, swipeDir);
                        mGeoObjects.remove(position);
                        mAdapter.notifyItemRemoved(position);

                        if (mGeoObjects.size() ==0) {
                            textView.setText("No more places to swipe");
                        }
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mGeoRecyclerView);

    }

    private void notifyUser(int position, int swipeDir) {
        GeoObject swipedObject = mGeoObjects.get(position);
        String result;
        if ((swipeDir == SWIPE_RIGHT_DIRECTION && !swipedObject.getmInEurope()) ||
            (swipeDir == SWIPE_LEFT_DIRECTION  &&  swipedObject.getmInEurope()) ){
            result = "THIS WAS CORRECT!!!";
        }
        else {
            result = "This is incorrect :(";
        }

        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG ).show();
    }
}
