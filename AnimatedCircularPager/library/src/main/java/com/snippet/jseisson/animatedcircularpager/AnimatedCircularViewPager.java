package com.snippet.jseisson.animatedcircularpager;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

public class AnimatedCircularViewPager extends ViewPager {

    private static int DEFAULT_DELAY = 3000;

    private int delay;
    private Timer timer;
    private CircularTimerTask timerTask;

    public AnimatedCircularViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEnabled(false);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.animatedCircularViewPager,
                0, 0);
        delay = a.getInteger(R.styleable.animatedCircularViewPager_delayInMillis, DEFAULT_DELAY);
    }

    public AnimatedCircularViewPager(Context context) {
        this(context, null);
    }

    public AnimatedCircularViewPager(Context context, int delay) {
        this(context, null);
        this.delay = delay;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (!(adapter instanceof AnimatedCircularPagerAdapter)) {
            throw new IllegalArgumentException("Adapter must implements AnimatedCircularPagerAdapter");
        }
        super.setAdapter(adapter);
    }

    public void startAnimatedCircularSwipe() {
        timer = new Timer();
        timerTask = new CircularTimerTask();
        timer.schedule(timerTask, 0);
    }

    public void pauseAnimatedCircularSwipe() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    private class CircularTimerTask extends TimerTask {
        @Override
        public void run() {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    timerTask = new CircularTimerTask();
                    //if it is the last item go to the first one without animations
                    if (getCurrentItem() == (getAdapter().getCount() - 1)) {
                        setCurrentItem(0, false);
                        timer.schedule(timerTask, 0);
                    } else {
                        setCurrentItem(getCurrentItem() + 1, true);
                        timer.schedule(timerTask, delay);
                    }
                }
            });
        }
    }
}
