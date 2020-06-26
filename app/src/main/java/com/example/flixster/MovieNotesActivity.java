package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.databinding.ActivityMovieNotesBinding;

import java.util.ArrayList;
import java.util.List;

public class MovieNotesActivity extends AppCompatActivity {

    public static final String TAG = "MovieNotesActivity";

    EditText addItem;
    Button saveBtn;
    RecyclerView rvItems;
    List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieNotesBinding binding = ActivityMovieNotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addItem = binding.addItem;
        saveBtn = binding.saveBtn;
        rvItems = binding.rvView;
        items = new ArrayList<>();

        //temporarily add content
        items.add("Movie x was really good. Highly reccomend.");
        items.add("Movie y was really bad. Do not like horror movies.");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addedItem = addItem.getText().toString();
                Log.d(TAG, "save button clicked with item: " + addedItem);
            }
        });
    }
}