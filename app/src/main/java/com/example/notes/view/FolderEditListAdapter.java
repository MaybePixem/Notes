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
import com.example.notes.model.Folder;

import java.util.List;

public class FolderEditListAdapter extends RecyclerView.Adapter<FolderEditListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button deleteBtn;
        private FolderDAO folderDAO = new FolderDAO();
        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.folderName);
            deleteBtn = (Button) itemView.findViewById(R.id.deleteBtn);
            deleteBtn.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Folder folder = folderList.get(position);
                folderList.remove(folder);
                folderDAO.deleteFolder(folder);
                notifyItemRemoved(position);
            });
        }

    }

    private List<Folder> folderList;

    public FolderEditListAdapter(List<Folder> folderList) {
        this.folderList = folderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View folderView = inflater.inflate(R.layout.folder_edit_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(folderView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Folder folder = folderList.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(folder.getName());
        Button button = holder.deleteBtn;
        button.setText("Löschen");
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }
}
