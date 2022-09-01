package com.buildappwithpaulo.spisakzakupovinu.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNote;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNote = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void update(Note note) {
        noteDao.update(note);
    }

    public void delete(Note note) {
        noteDao.delete(note);
    }
   public void deleteAllNotes() {
        noteDao.deleteAllNotes();
   }

    public LiveData <List <Note>> getAllNote (){
         return noteDao.getAllNotes();
    }


}
