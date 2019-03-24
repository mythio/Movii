package com.mythio.movii.activity.youtubeActivity;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mythio.movii.R;
import com.mythio.movii.model.video.Video;

import java.util.ArrayList;
import java.util.List;

import static com.mythio.movii.util.Constant.YOUTUBE_DEV_KEY;

public class YouTubePlayerActivity extends YouTubeFailureRecoveryActivity {

    private YouTubePlayerView playerView;
    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.player_youtube);

        Bundle b = getIntent().getExtras();
        ArrayList<Video> videos = b.getParcelableArrayList("data");

        if (videos != null) {
            for (Video video : videos) {
                urls.add(video.getKey());
            }
        }

        playerView = findViewById(R.id.player);
        playerView.initialize(YOUTUBE_DEV_KEY, this);
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
            player.loadVideos(urls);
        }
    }
}
