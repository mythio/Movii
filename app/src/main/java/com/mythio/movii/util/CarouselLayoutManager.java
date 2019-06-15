package com.mythio.movii.util;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CarouselLayoutManager extends LinearLayoutManager {

    private final float mShrinkAmount;
    private final float mShrinkDistance;

    public CarouselLayoutManager(Context context, int orientation, boolean reverseLayout, float shrinkAmount, float shrinkDistance) {
        super(context, orientation, reverseLayout);
        this.mShrinkAmount = shrinkAmount;
        this.mShrinkDistance = shrinkDistance;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrolled = super.scrollVerticallyBy(dy, recycler, state);
        float midpoint = getHeight() / 2.0f;
        float d0 = 0.0f;
        float d1 = mShrinkDistance * midpoint;
        float s0 = 1.0f;
        float s1 = 1.0f - mShrinkAmount;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childMidpoint = (getDecoratedBottom(child) + getDecoratedTop(child)) / 2.0f;
            float d = Math.min(d1, Math.abs(midpoint - childMidpoint));
            float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);
            child.setScaleX(scale);
            child.setScaleY(scale);
        }
        return scrolled;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrolled = super.scrollHorizontallyBy(dx, recycler, state);
        float midpoint = getWidth() / 2.0f;
        float d0 = 0.0f;
        float d1 = mShrinkDistance * midpoint;
        float s0 = 1.0f;
        float s1 = 1.0f - mShrinkAmount;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2.0f;
            float d = Math.min(d1, Math.abs(midpoint - childMidpoint));
            float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);

            child.setScaleX(scale);
            child.setScaleY(scale);
        }
        return scrolled;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        scrollVerticallyBy(0, recycler, state);
    }
}