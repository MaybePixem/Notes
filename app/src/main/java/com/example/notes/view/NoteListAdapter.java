package com.example.notes.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.model.Folder;
import com.example.notes.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.noteName);
            nameTextView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), NoteTextView.class);
                intent.putExtra("folderId", noteList.get(getAdapterPosition()).getFolder());
                intent.putExtra("noteId", noteList.get(getAdapterPosition()).getId().toString());
                v.getContext().startActivity(intent);
            });

        }
    }

    private List<Note> noteList;

    public NoteListAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View noteView = inflater.inflate(R.layout.note_list_row, parent, false);

        // Return a new holder instance
        NoteListAdapter.ViewHolder viewHolder = new NoteListAdapter.ViewHolder(noteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(note.getTitle());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
