package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    public final String MOVIE_URL = "https://api.themoviedb.org/3/movie/tKodtNFpzBA/videos?api_key=" + getString(R.string.youtube_api_key) + "&language=en-US";

    // temporary test video id -- TODO replace with movie trailer video id (also in MOVIE_URL)
    final String videoId = "tKodtNFpzBA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);



        // resolve the player view from the layout
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);

        // initialize with API key stored in secrets.xml
        playerView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                Log.d("MovieTrailerActivity", "onSuccess");

                youTubePlayer.cueVideo(videoId);


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
    }
}