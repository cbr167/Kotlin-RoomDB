package notesapp.raj.com.notes.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import notesapp.raj.com.notes.database.Note
import notesapp.raj.com.notes.database.NoteDao
import notesapp.raj.com.notes.database.NoteDatabase

class NoteRepository(application: Application) {

    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note>>

    init {
        val database = NoteDatabase.getInstance(application)
        noteDao = database.noteDao()
        allNotes = noteDao.allNotes
    }

    fun deleteAllNotes() {
        DeleteAllNoteAsynctask(noteDao).execute()
    }

    fun insert(note: Note) {

        InsertNoteAsynctask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteNoteAsynctask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsynctask(noteDao).execute(note)
    }


    private class InsertNoteAsynctask (private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.insert(notes[0])
            return null
        }
    }


    private class UpdateNoteAsynctask (private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.update(notes[0])
            return null
        }
    }

    private class DeleteNoteAsynctask(private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.delete(notes[0])
            return null
        }
    }


    private class DeleteAllNoteAsynctask (private val noteDao: NoteDao) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg voids: Void): Void? {
            noteDao.deleteAllNotes()
            return null
        }
    }
}
