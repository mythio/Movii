//package com.mythio.movii.adapter;
//
//import android.content.res.ColorStateList;
//import android.graphics.Color;
//import android.support.annotation.NonNull;
//import android.support.v4.view.ViewPager;
//import android.util.Log;
//import android.view.View;
//
//public class ZoomTranformation implements ViewPager.PageTransformer {
//    @Override
//    public void transformPage(@NonNull View pager, float v) {
//        int pageWidth = pager.getMeasuredWidth() -
//                pager.getPaddingLeft() - pager.getPaddingRight();
//        int paddingLeft = pager.getPaddingLeft();
//        float transformPos = (float) (page.getLeft() -
//                (pager.getScrollX() + paddingLeft)) / pageWidth;
//
//        if (transformPos < -1){
//            page.setScaleY(0.8f);
//        } else if (transformPos <= 1) {
//            page.setScaleY(1f);
//        } else {
//            page.setScaleY(0.8f);
//        }
//    }
//}