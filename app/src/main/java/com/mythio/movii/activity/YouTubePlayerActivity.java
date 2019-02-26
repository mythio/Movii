package com.mythio.movii.activity;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mythio.movii.R;

import static com.mythio.movii.constant.Constants.YOUTUBE_DEVELOPER_KEY;

public class YouTubePlayerActivity extends YouTubeFailureRecoveryActivity {

    private YouTubePlayerView playerView;
    private String url;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.player_youtube);

        url = getIntent().getStringExtra("MOVIE_YOUTUBE_KEY");

        playerView = findViewById(R.id.player);
        playerView.initialize(YOUTUBE_DEVELOPER_KEY, this);
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        player.setFullscreen(true);
        player.setShowFullscreenButton(false);
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        if (!wasRestored) {
            player.loadVideo(url);
        }
    }
}
