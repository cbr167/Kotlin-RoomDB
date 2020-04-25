package notesapp.raj.com.notes.interfaces

import notesapp.raj.com.notes.database.Note

/**
 * Created by bhagy on 4/19/2020.
 */
interface OnItemClickListener {
    fun onRowClick(note: Note)
}