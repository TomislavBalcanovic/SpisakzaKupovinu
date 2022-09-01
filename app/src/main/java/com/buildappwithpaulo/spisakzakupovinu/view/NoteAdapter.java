package com.buildappwithpaulo.spisakzakupovinu.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.buildappwithpaulo.spisakzakupovinu.R;
import com.buildappwithpaulo.spisakzakupovinu.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewNamirnica.setText(currentNote.getNamirnica());
        holder.textViewKolicina.setText(currentNote.getKolicina());
        holder.textViewRedosled.setText(String.valueOf(currentNote.getRedosled()));


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private final TextView textViewNamirnica;
        private final TextView textViewKolicina;
        private final TextView textViewRedosled;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            textViewNamirnica = itemView.findViewById(R.id.text_view_namirnica);
            textViewKolicina = itemView.findViewById(R.id.text_view_kolicina);
            textViewRedosled = itemView.findViewById(R.id.text_view_redosled);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                        listener.onItemClick(notes.get(position));

                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
