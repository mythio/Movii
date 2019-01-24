package com.mythio.movii.activity;

import android.os.Bundle;


import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mythio.movii.R;

/**
 * Created by Anu S Pillai on 7/12/2016.
 */
public class YoutubePlayer extends YouTubeFailureRecoveryActivity{


        private YouTubePlayerView playerView;
        private String youtube_url;
        String DEVELOPER_KEY = "AIzaSyCNTF1Wn34PQtvaJIUB4_iAB2KF8Wrympg"; //Change this



        @Override
        protected void onCreate(Bundle bundle) {
                super.onCreate(bundle);
                setContentView(R.layout.player_youtube);

             youtube_url =    "bdB_CL-daxA" ;

                playerView = (YouTubePlayerView) findViewById(R.id.player);
                playerView.initialize(DEVELOPER_KEY, this);
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
                        player.loadVideo(youtube_url);
                      //  player.loadVideo("l4E4XC7qOfk");
                }

        }
}
