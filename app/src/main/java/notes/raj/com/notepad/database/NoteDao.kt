package notes.raj.com.notepad.database


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

//to define database operations that we want to make on Note entity.
//Good approach to have one dao for one entity
@Dao
interface NoteDao {

    @get:Query("SELECT * FROM note_table")
    val allNotes: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()
}
