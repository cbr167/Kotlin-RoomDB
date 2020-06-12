package notes.raj.com.notepad.interfaces

import notes.raj.com.notepad.database.Note

/**
 * Created by bhagy on 4/19/2020.
 */
interface OnItemClickListener {
    fun onRowClick(note: Note)
}