package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflator;
    List<Note> notes;

    Adapter(Context context, List<Note> notes) {
        inflator = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflator.inflate(R.layout.custom_list_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();
        long id = notes.get(position).getId();
        Log.d("title", "onBindViewHolder: " + title);

        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);
        holder.nID.setText(String.valueOf(notes.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nTitle, nDate, nTime, nID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);
            nID = itemView.findViewById(R.id.listId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(v.getContext(), Details.class);
                    i.putExtra("ID", notes.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
