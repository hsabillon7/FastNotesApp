package tech.twentytwobits.fastnotesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import tech.twentytwobits.fastnotesapp.NotesDao.NotesDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var notesDatabase: NotesDatabase
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesDatabase = NotesDatabase.getInstance(this)!!
        // Cargar los valores de la base de datos al adaptador
        noteAdapter = NoteAdapter(NotesDatabase.getNoteDao().getNoteList())
    }
}
