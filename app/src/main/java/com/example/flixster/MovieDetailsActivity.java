package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

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
    Button moreBtn;

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
        moreBtn = binding.moreBtn;

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
                .placeholder(R.drawable.flicks_backdrop_placeholder)
                .error(R.drawable.flicks_backdrop_placeholder)
                .into(bckImage);

        // set info for recommended movie
        otherTitle.setText(otherMovie.getTitle());
        otherOverview.setText(otherMovie.getOverview());

        float otherVoteAverage = otherMovie.getVoteAverage().floatValue();
        otherRbVoteAverage.setRating(otherVoteAverage = otherVoteAverage > 0 ? otherVoteAverage / 2.0f : otherVoteAverage);

        String otherImageUrl = otherMovie.getPosterPath();
        Glide.with(this)
                .load(otherImageUrl)
                .placeholder(R.drawable.flicks_movie_placeholder)
                .error(R.drawable.flicks_movie_placeholder)
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

        //when moreBtn is clicked, send to MovieDetailsActivity with new movie
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "more button clicked");
                // create intent for the new activity
                Intent intent = new Intent(MovieDetailsActivity.this, MovieDetailsActivity.class);
                // serialize the movie using parceler
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(otherMovie));
                intent.putExtra("OTHER_MOVIE", Parcels.wrap(movie));
                // show the activity
                startActivityForResult(intent, TRAILER_CODE);
            }
        });
    }
}