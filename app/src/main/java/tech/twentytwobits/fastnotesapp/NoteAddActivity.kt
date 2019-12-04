package tech.twentytwobits.fastnotesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_note_add.*
import tech.twentytwobits.fastnotesapp.NotesDao.Note
import tech.twentytwobits.fastnotesapp.NotesDao.NotesDatabase

class NoteAddActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private lateinit var notesDatabase: NotesDatabase
    private var priority = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)

        // Verificar si existe una instancia de la base de datos
        notesDatabase = NotesDatabase.getInstance(this)!!

        // Colocar un listener para el radio button
        radioGroupPriority.setOnCheckedChangeListener(this)

        // Verificar si se envía mediante un Intent información sobre una nota
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        // Si no está definido el título o viene en blanco o el usuario
        // accede mediante el FAB
        if (title == null || title == "") {
            buttonAdd.setOnClickListener {
                val note = Note(editTextTitle.text.toString(), editTextDescription.text.toString(), priority)
                notesDatabase.getNoteDao().saveNote(note)
                finish()
            }
        } else {
            val id = intent.getIntExtra("id", 0)
            // Cargar los valores en el layout
            editTextTitle.setText(title)
            editTextDescription.setText(description)

            buttonAdd.setOnClickListener {
                val note = Note(editTextTitle.text.toString(), editTextDescription.text.toString(), priority)
                note.id = id
                notesDatabase.getNoteDao().updateNote(note)
                finish()
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.rbMedia) {
            priority = 2
        } else if (checkedId == R.id.rbAlta) {
            priority = 3
        }
    }
}
