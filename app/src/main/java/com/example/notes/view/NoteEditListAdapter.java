package com.example.notes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.data.FolderDAO;
import com.example.notes.data.NoteDAO;
import com.example.notes.model.Folder;
import com.example.notes.model.Note;

import java.util.List;

public class NoteEditListAdapter extends RecyclerView.Adapter<NoteEditListAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button deleteBtn;
        private NoteDAO noteDAO = new NoteDAO();

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.noteName);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            deleteBtn.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Note note = noteList.get(position);
                noteList.remove(note);
                noteDAO.deleteNote(note);
                notifyItemRemoved(position);
            });
        }

    }

    private List<Note> noteList;

    public NoteEditListAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteEditListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View noteView = inflater.inflate(R.layout.note_edit_row, parent, false);

        NoteEditListAdapter.ViewHolder viewHolder = new NoteEditListAdapter.ViewHolder(noteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(note.getTitle());
        Button button = holder.deleteBtn;
        button.setText("Löschen");
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
