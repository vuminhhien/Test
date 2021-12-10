package com.minhien.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    ImageView img = null;
    final int[] drawable = new int[]{
            R.drawable.c01, R.drawable.c02, R.drawable.c03, R.drawable.c04, R.drawable.c05, R.drawable.c06, R.drawable.c07, R.drawable.c08,
            R.drawable.c09, R.drawable.c10, R.drawable.c11, R.drawable.c12, R.drawable.c13, R.drawable.c14, R.drawable.m00, R.drawable.m01,
            R.drawable.m02, R.drawable.m03, R.drawable.m04, R.drawable.m05, R.drawable.m06, R.drawable.m07, R.drawable.m08, R.drawable.m09,
            R.drawable.m10, R.drawable.m11, R.drawable.m12, R.drawable.m13, R.drawable.m14, R.drawable.m15, R.drawable.m16, R.drawable.m17,
            R.drawable.m18, R.drawable.m19, R.drawable.m20, R.drawable.m21,
    };
    int[] pos = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35};
    int currentPos = -1;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridview);
        ImageAdapter adapter = new ImageAdapter(MainActivity.this);
        gridView.setAdapter(adapter);
        shuffleArray(pos);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentPos < 0) {
                    currentPos = i;
                    img = (ImageView) view;
                    ((ImageView) view).setImageResource(drawable[pos[i]]);
                    currentPos = -1;
                }
            }
        });
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