package com.example.flixster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;
import com.example.flixster.databinding.ItemNoteBinding;
import com.example.flixster.models.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    Context context;
    List<Note> items;
    OnLongClickListener longClickListener;

    public NoteAdapter(Context context, List<Note> items, OnLongClickListener longClickListener) {
        this.context = context;
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate a view
        View notesView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(notesView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the position
        Note item = items.get(position);
        //Bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        //TextView tvItem;
        TextView tvTitle;
        TextView tvReview;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ItemNoteBinding binding = ItemNoteBinding.bind(itemView);

            tvTitle = binding.tvTitle;
            tvReview = binding.tvReview;
            //tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(Note item) {
            tvTitle.setText(item.getTitle());
            tvReview.setText(item.getRating());

            tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //notify the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
