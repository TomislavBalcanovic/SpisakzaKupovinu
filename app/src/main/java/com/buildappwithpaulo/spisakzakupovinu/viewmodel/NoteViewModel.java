package com.buildappwithpaulo.spisakzakupovinu.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.buildappwithpaulo.spisakzakupovinu.model.Note;
import com.buildappwithpaulo.spisakzakupovinu.model.NoteDao;
import com.buildappwithpaulo.spisakzakupovinu.model.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNote;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository = new NoteRepository(application);
        allNote = repository.getAllNote();

    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(note).execute();
    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask().execute();
    }

    public LiveData<List<Note>> getAllNote() {
        return allNote;
    }

    private class DeleteNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private final Note note;

        private DeleteNoteAsyncTask(Note note) {
            this.note = note;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.delete(note);
            return null;
        }
    }

    private class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {


        private DeleteAllNoteAsyncTask() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.deleteAllNotes();
            return null;
        }
    }

}
