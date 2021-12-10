package com.minhien.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.minhien.testapp.Helper.MyItemTouchHelperCallback;
import com.minhien.testapp.Helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView rcv;

    ItemTouchHelper itemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        generateItem();
    }

    private void generateItem() {
        List<Integer> data = new ArrayList<>();
        data.addAll(Arrays.asList(
                R.drawable.c01, R.drawable.c02, R.drawable.c03, R.drawable.c04, R.drawable.c05, R.drawable.c06, R.drawable.c07, R.drawable.c08,
                R.drawable.c09, R.drawable.c10, R.drawable.c11, R.drawable.c12, R.drawable.c13, R.drawable.c14, R.drawable.m00, R.drawable.m01,
                R.drawable.m02, R.drawable.m03, R.drawable.m04, R.drawable.m05, R.drawable.m06, R.drawable.m07, R.drawable.m08, R.drawable.m09,
                R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16, R.drawable.m17,
                R.drawable.m18, R.drawable.m19, R.drawable.m20, R.drawable.m21
        ));

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this, data, new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                itemTouchHelper.startDrag(viewHolder);
            }
        });
        rcv.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rcv);
    }

    public void init(){
        ButterKnife.bind(this);
        int count = 2;
        rcv.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,count);
        rcv.setLayoutManager(gridLayoutManager);
    }

    static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}