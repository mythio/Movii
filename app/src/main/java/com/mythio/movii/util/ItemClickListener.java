package com.mythio.movii.util;

public interface ItemClickListener {

    interface OnItemClick {
        void onItemClick(int position);
    }

    interface OnItemClickData<T> {
        void onItemClick(int position, T t);
    }
}