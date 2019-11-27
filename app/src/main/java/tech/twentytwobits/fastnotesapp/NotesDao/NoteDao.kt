package tech.twentytwobits.fastnotesapp.NotesDao

import androidx.room.*

@Dao
interface NoteDao {
    /**
     * Retorna todos los registros de Note en orden ascendente
     */
    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getNoteList(): List<Note>

    /**
     * Retorna un registro de Note
     * @param id el valor de la llave primaria de Note
     */
    @Query("SELECT * FROM note WHERE id = :id")
    fun getNote(id: Int): Note

    /**
     * Insertar un nuevo registro en Note
     * @param note el valor del registro a insertar
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveNote(note: Note)

    /**
     * Actualizar un registro en Note
     * @param note el valor del registro a actualizar
     */
    @Update
    fun updateNote(note: Note)

    /**
     * Remover un registro de Note
     * @param note el valor del registro a remover
     */
    @Delete
    fun deleteNote(note: Note)
}