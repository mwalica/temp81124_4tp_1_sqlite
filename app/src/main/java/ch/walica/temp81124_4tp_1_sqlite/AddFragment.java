package ch.walica.temp81124_4tp_1_sqlite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import ch.walica.temp81124_4tp_1_sqlite.database.DatabaseHelper;
import ch.walica.temp81124_4tp_1_sqlite.model.Note;


public class AddFragment extends Fragment {

    EditText etTitle, etDescription;
    Button btnAdd;
    MaterialToolbar materialToolbar;
    DatabaseHelper databaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTitle = view.findViewById(R.id.etTitle);
        etDescription = view.findViewById(R.id.etDescription);
        btnAdd = view.findViewById(R.id.btnAdd);
        materialToolbar = view.findViewById(R.id.topAppBar2);
        databaseHelper = new DatabaseHelper(requireContext());

        materialToolbar.setNavigationOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentManager, new HomeFragment()).commit();
        });

        btnAdd.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            if (!title.isEmpty() && !description.isEmpty()) {
                Note note = new Note(title, description);
                databaseHelper.addNote(note);
                etTitle.getText().clear();
                etDescription.getText().clear();
            } else {
                Toast.makeText(requireContext(), "Proszę wypełnić formularz", Toast.LENGTH_SHORT).show();
            }
        });
    }
}