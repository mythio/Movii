package com.mythio.movii.util;

public interface ItemClickListener {

    interface OnItemClick {
        void onItemClick(int id);
    }

    interface OnItemClickData<T> {
        void onItemClick(int id, T t);
    }
}