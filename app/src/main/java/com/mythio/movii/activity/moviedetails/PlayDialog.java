package com.mythio.movii.activity.moviedetails;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mythio.movii.R;
import com.mythio.movii.activity.moviedetails.playdialogcontract.Contract;
import com.mythio.movii.activity.moviedetails.playdialogcontract.Presenter;

import butterknife.ButterKnife;

public class PlayDialog extends BottomSheetDialogFragment implements Contract.View {

//    @BindView(R.id.btn_trailer)
//    Button btnTrailer;
//
//    @BindView(R.id.btn_stream)
//    Button btnStream;

    private Contract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPresenter(new Presenter(this));
        ButterKnife.bind(this, view);

        Log.d("TAG_TAG_TAG", "play dialog");
    }

    @Override
    public void initView() {

    }

    @Override
    public void launchYoutubePlayer() {

    }

    @Override
    public void launchBrowser() {

    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}