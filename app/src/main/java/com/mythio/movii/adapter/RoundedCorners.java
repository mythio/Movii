package com.mythio.movii.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

public class RoundedCorners implements Transformation {
    private final int radius;
    private final Corners corners;

    public enum Corners {
        TOP(0x8),  // binary: 1000
        BOTTOM(0x4),  // binary: 0100
        LEFT(0x2),  // binary: 0010
        RIGHT(0x1),  // binary: 0001
        TOP_LEFT(0xA),  // binary: 1010
        TOP_RIGHT(0x9),  // binary: 1001
        BOTTOM_LEFT(0x6),  // binary: 0110
        BOTTOM_RIGHT(0x5),  // binary: 0101
        ALL(0xF);  // binary: 1111

        private int code;

        private Corners(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public RoundedCorners(int radius, Corners corners) {
        this.radius = radius;
        this.corners = corners;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int w = source.getWidth();
        int h = source.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        final float roundPx = radius;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        boolean top = (corners.getCode() & Corners.TOP.getCode()) > 0;
        boolean bottom = (corners.getCode() & Corners.BOTTOM.getCode()) > 0;
        boolean left = (corners.getCode() & Corners.LEFT.getCode()) > 0;
        boolean right = (corners.getCode() & Corners.RIGHT.getCode()) > 0;
        canvas.drawRect(left ? roundPx : 0,
                top ? roundPx : 0,
                right ? w - roundPx : w,
                bottom ? h - roundPx : h,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, rect, rect, paint);
        source.recycle();
        return output;
    }

    @Override
    public String key() {
        return "rounded();";
    }
}