package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flixster.adapters.NoteAdapter;
import com.example.flixster.databinding.ActivityMovieNotesBinding;
import com.example.flixster.models.Note;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.readLines;

public class MovieNotesActivity extends AppCompatActivity {

    public static final String TAG = "MovieNotesActivity";

    EditText addItem;
    EditText addTitle;
    Button saveBtn;
    RecyclerView rvItems;
    List<Note> items;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieNotesBinding binding = ActivityMovieNotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addItem = binding.addItem;
        addTitle = binding.addTitle;
        saveBtn = binding.saveBtn;
        rvItems = binding.rvView;

        items = new ArrayList<Note>();
       //loadItems();

        NoteAdapter.OnLongClickListener onLongClickListener = new NoteAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                //delete the item from the model
                items.remove(position);
                //notify the adapter
                noteAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
              //  saveItems();
            }
        };

        noteAdapter = new NoteAdapter(this, items, onLongClickListener);
        rvItems.setAdapter(noteAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addedItem = addItem.getText().toString();
                String addedTitle = addTitle.getText().toString();
                Log.d(TAG, "save button clicked with title: " + addedTitle);

                //add items to model
                items.add(new Note(addedItem, addedTitle));

                //notify adapter that an item is inserted
                noteAdapter.notifyItemInserted(items.size() - 1);
                addItem.setText("");
                addTitle.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
              //  saveItems();
            }
        });
    }

    //file to hold items for persistence
    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }

    //load items by reading every line of the data file
  // public void loadItems() {
     //  try {
         // items = new ArrayList<Note>(readLines(getDataFile(), Charset.defaultCharset()));
   //     } catch (IOException e) {
   //         Log.e(TAG, "Error reading items", e);
   //         items = new ArrayList<>();
   //     }
  // }

    //saves items by writing them into the data file
  //  public void saveItems() {
   //     try {
   //         org.apache.commons.io.FileUtils.writeLines(getDataFile(), items);
   //     } catch (IOException e) {
   //         Log.e(TAG, "Error writing items", e);
   //     }
   // }
}