package com.snippet.jseisson.animatedcircularpager.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snippet.jseisson.animatedcircularpager.AnimatedCircularPagerAdapter;

import java.util.List;

public class CustomPager extends AnimatedCircularPagerAdapter<List<String>> {

    private final Context context;

    public CustomPager(List<String> list, Context context) {
        super(list);
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item, collection, false);
        ((TextView) layout.findViewById(R.id.text)).setText(list.get(position));
        int color = getColorFromString(list.get(position));
        ((ImageView) layout.findViewById(R.id.image)).setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        ((TextView) layout.findViewById(R.id.text)).setTextColor(color);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private int getColorFromString(String color) {
        switch (color) {
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Red":
                return Color.RED;
            case "Magenta":
                return Color.MAGENTA;
            default:
                return Color.BLACK;

        }

    }
}
