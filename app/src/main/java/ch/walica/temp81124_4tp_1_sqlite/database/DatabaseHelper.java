package ch.walica.temp81124_4tp_1_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import ch.walica.temp81124_4tp_1_sqlite.model.Note;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, "notes_db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, create_date INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS notes";
        db.execSQL(query);
        this.onCreate(db);
    }

    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", note.getTitle());
        cv.put("description", note.getDescription());
        cv.put("create_date", note.getCreateDate());

        long result = db.insert("notes", null, cv);
        if(result == -1) {
            Toast.makeText(context, "Błąd przy dodawaniu notatki", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Notatka dodana do bazy danych", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM notes";
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("notes", "id=?", new String[]{String.valueOf(id)});
        if(result == -1) {
            Toast.makeText(context, "Błąd usuwania notatki", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Notatka usunięta z bazy danych", Toast.LENGTH_SHORT).show();
        }
    }
}
