package com.buildappwithpaulo.spisakzakupovinu.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.buildappwithpaulo.spisakzakupovinu.R;

import com.buildappwithpaulo.spisakzakupovinu.model.Note;
import com.buildappwithpaulo.spisakzakupovinu.viewmodel.AddNoteViewModel;



public class AddNoteFragment extends Fragment {

    private EditText editTextNamirnica;
    private EditText editTextKolicina;
    private TextView textViewIzaberiRedosled;
    private NumberPicker numberPicker;
    private AddNoteViewModel addNoteViewModel;
    private boolean isUpdate;
    private Note note;

    public AddNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextNamirnica = view.findViewById(R.id.edit_text_namirnica);
        editTextKolicina = view.findViewById(R.id.edit_text_kolicina);
        textViewIzaberiRedosled = view.findViewById(R.id.text_view_izaberi_redosled);

        numberPicker = view.findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);

        setHasOptionsMenu(true);

        addNoteViewModel = ViewModelProviders.of(this).get(AddNoteViewModel.class);

        note = AddNoteFragmentArgs.fromBundle(getArguments()).getNoteparc();
        if (note == null) {
            isUpdate = false;
            Toast.makeText(getContext(), "Note is empty", Toast.LENGTH_SHORT).show();
        } else
            isUpdate = true;
        Toast.makeText(getContext(), "Note is not empty", Toast.LENGTH_SHORT).show();

    }

    public void updateNote(String title, String description, int priority) {

     note.setNamirnica(title);
     note.setKolicina(description);
     note.setRedosled(priority);

     addNoteViewModel.update(note);
        Toast.makeText(getContext(), "Note updated", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).popBackStack();

    }

    public void saveNote(String title, String description, int priority) {

        Note note = new Note(title, description, priority);
        addNoteViewModel.insert(note);
        Toast.makeText(getContext(), "Note saved", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).popBackStack();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_note_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note: {
                String title = editTextNamirnica.getText().toString();
                String description = editTextKolicina.getText().toString();
                int priority = numberPicker.getValue();
                if (title.trim().isEmpty() || description.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Ubacite Hranu i kolicinu ", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (isUpdate) {
                    updateNote(title, description, priority);
                } else {
                    saveNote(title, description, priority);
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
