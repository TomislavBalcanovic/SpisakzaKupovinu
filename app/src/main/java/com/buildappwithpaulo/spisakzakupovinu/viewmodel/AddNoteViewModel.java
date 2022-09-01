package com.buildappwithpaulo.spisakzakupovinu.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.buildappwithpaulo.spisakzakupovinu.model.Note;
import com.buildappwithpaulo.spisakzakupovinu.model.NoteDao;
import com.buildappwithpaulo.spisakzakupovinu.model.NoteRepository;

public class AddNoteViewModel extends AndroidViewModel {

    public NoteRepository repository;

    public AddNoteViewModel(@NonNull Application application) {
        super(application);

        repository = new NoteRepository(application);
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(note).execute();
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(note).execute();
    }

    private class InsertNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private final Note note;

        private InsertNoteAsyncTask(Note note) {
            this.note = note;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.insert(note);
            return null;
        }
    }

    private class UpdateNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private final Note note;

        private UpdateNoteAsyncTask(Note note) {
            this.note = note;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.update(note);
            return null;
        }
    }
}
