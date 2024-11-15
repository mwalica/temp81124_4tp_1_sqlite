package ch.walica.temp81124_4tp_1_sqlite;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ch.walica.temp81124_4tp_1_sqlite.adapter.NoteAdapter;
import ch.walica.temp81124_4tp_1_sqlite.database.DatabaseHelper;
import ch.walica.temp81124_4tp_1_sqlite.model.Note;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    TextView tvMsg;
    FloatingActionButton floatingActionButton;
    List<Note> notes = new ArrayList<>();
    DatabaseHelper databaseHelper;
    NoteAdapter noteAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteAdapter = new NoteAdapter(notes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        tvMsg = view.findViewById(R.id.tvMsg);
        floatingActionButton = view.findViewById(R.id.floatingActionButton2);
        databaseHelper = new DatabaseHelper(requireContext());

        setNotesInList();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(noteAdapter);

        floatingActionButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentManager, new AddFragment()).commit();
        });
    }

    private void setNotesInList() {
        Cursor cursor = databaseHelper.getAllNotes();
        if(cursor.getCount() == 0) {
            tvMsg.setVisibility(View.VISIBLE);
        } else {
            tvMsg.setVisibility(View.GONE);
            while(cursor.moveToNext()) {
                Note note = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3));
                notes.add(note);
            }
        }
        Log.d("my_log", "setNotesInList: " + notes.size());
    }
}