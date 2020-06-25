package com.example.flixster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.flixster.databinding.ActivityMainBinding;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {
    public static final String KEY_ITEM_ID = "movie_id";
    public static final int TRAILER_CODE = 20;
    public static final String TAG = "MovieDetailsActivity";

    Movie movie;
    Movie otherMovie;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    Button vidBtn;
    Button txBtn;
    ImageView bckImage;
    TextView otherTitle;
    TextView otherOverview;
    RatingBar otherRbVoteAverage;
    ImageView otherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        rbVoteAverage = binding.rbVoteAverage;
        vidBtn = binding.vidBtn;
        txBtn = binding.txBtn;
        bckImage = binding.backImg;
        otherTitle = binding.otherTitle;
        otherOverview = binding.otherOverview;
        otherRbVoteAverage = binding.otherRbVoteAverage;
        otherImage = binding.otherImage;

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d(TAG, String.format("Showing details for '%s'", movie.getTitle()));

        otherMovie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("OTHER_MOVIE"));
        Log.d(TAG, String.format("Showing details for recommended movie: '%s'", otherMovie.getTitle()));

        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);

        String imageUrl = movie.getBackdropPath();
        Glide.with(this)
                .load(imageUrl)
                .into(bckImage);

        // set info for recommended movie
        otherTitle.setText(otherMovie.getTitle());
        otherOverview.setText(otherMovie.getOverview());

        float otherVoteAverage = otherMovie.getVoteAverage().floatValue();
        otherRbVoteAverage.setRating(otherVoteAverage = otherVoteAverage > 0 ? otherVoteAverage / 2.0f : otherVoteAverage);

        String otherImageUrl = otherMovie.getPosterPath();
        Glide.with(this)
                .load(otherImageUrl)
                .into(otherImage);

        //when vidBtn is clicked, send to MovieTrailerActivity with id of movie
        vidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button clicked for movie: " + movie.getId());
                Intent i = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                i.putExtra(KEY_ITEM_ID, movie.getId());
                startActivityForResult(i, TRAILER_CODE);
            }
        });

        //when txBtn is clicked, send to WebView to display fandango
        txBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "buy tickets button clicked");
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("http://www.fandango.com");
            }
        });
    }
}