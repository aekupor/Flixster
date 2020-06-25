package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class MovieTrailerActivity extends YouTubeBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MovieTrailerActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        // resolve the player view from the layout
        final YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);

        final int id = getIntent().getIntExtra(MovieDetailsActivity.KEY_ITEM_ID, 0);
        Log.d("MovieTrailerActivity", "movie id: " + id);

        String MOVIE_URL = "https://api.themoviedb.org/3/movie/" + id +"/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        //call to movie API to get video id
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIE_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i("MovieTrailerActivity", "Results: " + results.toString());
                    JSONObject movie = results.getJSONObject(0);
                    final String movie_id = movie.getString("key");

                    // initialize with API key stored in secrets.xml; call to YouTube API
                    playerView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {
                            Log.d("MovieTrailerActivity", "onSuccess with movie_id: " + movie_id);
                            youTubePlayer.cueVideo(movie_id);
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {
                            Log.e("MovieTrailerActivity", "Error initializing YouTube player");
                        }
                    });

                } catch (JSONException e) {
                    Log.e("MovieTrailerActivity", "Hit json exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d("MovieTrailerActivity", "onFailure");
            }
        });
    }
}