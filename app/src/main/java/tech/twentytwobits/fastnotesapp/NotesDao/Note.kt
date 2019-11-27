package tech.twentytwobits.fastnotesapp.NotesDao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class Note (
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "detail")
    var detail: String = "",
    @ColumnInfo(name = "priority")
    var priority: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}