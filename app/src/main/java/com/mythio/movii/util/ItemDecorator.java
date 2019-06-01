package com.mythio.movii.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDecorator extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private final int space;
    private final int style;

    public ItemDecorator(int space, int style) {
        this.space = space;
        this.style = style;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        switch (style) {
            case HORIZONTAL:
                if (parent.getChildAdapterPosition(view) != 0) {
                    outRect.left = space;
                }
                break;
            case VERTICAL:
                outRect.bottom = space;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = space;
                }
                break;
        }
    }
}
