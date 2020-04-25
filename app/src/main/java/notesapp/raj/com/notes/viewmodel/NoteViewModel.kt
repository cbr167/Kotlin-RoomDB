package notesapp.raj.com.notes.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import notesapp.raj.com.notes.database.Note
import notesapp.raj.com.notes.repository.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    public val noteRepository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        noteRepository = NoteRepository(application)
        allNotes = noteRepository.allNotes
    }

    fun insert(note: Note) {
        noteRepository.insert(note)
    }

    fun delete(note: Note) {
        noteRepository.delete(note)
    }
    fun deleteAllNotes() {
        noteRepository.deleteAllNotes()
    }


    fun update(note: Note) {
        noteRepository.update(note)
    }
}
