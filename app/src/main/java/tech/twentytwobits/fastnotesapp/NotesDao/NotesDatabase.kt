package tech.twentytwobits.fastnotesapp.NotesDao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    // Patrón de diseño singleton
    // https://medium.com/better-programming/what-is-a-singleton-2dc38ca08e92
    companion object {
        val database = "notesdatabase"
        var notesDatabase: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase? {
            if (notesDatabase == null) {
                notesDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    NotesDatabase.database
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return notesDatabase
        }
    }
}