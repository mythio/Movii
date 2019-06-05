package com.mythio.movii.activity.movie_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mythio.movii.R;
import com.mythio.movii.util.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.mythio.movii.util.Constant.IMAGE_BASE_URL;

public class CastBottomDialog extends BottomSheetDialogFragment {

    @BindView(R.id.cast)
    CircleImageView imageView;

    @BindView(R.id.txt_view_cast)
    TextView textViewCast;

    @BindView(R.id.txt_view_character)
    TextView textViewCharacter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_cast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        String url = bundle.getString("123");

        ImageView imageView = view.findViewById(R.id.cast);

        textViewCast.setText(bundle.getString("234"));
        textViewCharacter.setText("as " + bundle.getString("345"));

        Glide.with(App.getContext())
                .load(IMAGE_BASE_URL + "w185" + url)
                .into(imageView);
    }
}