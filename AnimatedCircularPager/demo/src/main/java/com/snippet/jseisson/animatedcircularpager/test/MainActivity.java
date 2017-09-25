package com.snippet.jseisson.animatedcircularpager.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.snippet.jseisson.animatedcircularpager.AnimatedCircularViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AnimatedCircularViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (AnimatedCircularViewPager) findViewById(R.id.viewpager);

        String[] arrayList = getResources().getStringArray(R.array.sample);
        List<String> stringList = new ArrayList<>();

        Collections.addAll(stringList, arrayList);

        CustomPager adapter = new CustomPager(stringList, this);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.startAnimatedCircularSwipe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.pauseAnimatedCircularSwipe();
    }
}
