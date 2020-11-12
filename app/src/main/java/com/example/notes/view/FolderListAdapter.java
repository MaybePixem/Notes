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

import java.util.List;

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.folderName);
            nameTextView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), NotesActivity.class);
                intent.putExtra("folderId", folderList.get(getAdapterPosition()).getName());
                v.getContext().startActivity(intent);
            });

        }
    }

    private List<Folder> folderList;

    public FolderListAdapter(List<Folder> folderList) {
        this.folderList = folderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View folderView = inflater.inflate(R.layout.folder_list_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(folderView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Folder folder = folderList.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(folder.getName());
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }
}
