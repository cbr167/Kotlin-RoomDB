package notesapp.raj.com.notes.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = arrayOf(Note::class), version = 2)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao


    private class PopulateDbAynctask (noteDatabase: NoteDatabase) : AsyncTask<Void, Void, Void>() {
        private val noteDao: NoteDao

        init {
            noteDao = noteDatabase.noteDao()
        }

        override fun doInBackground(vararg voids: Void): Void? {
            return null
        }
    }

    companion object {

        private var instance: NoteDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NoteDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
            }
            return instance as NoteDatabase
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAynctask(instance as NoteDatabase).execute()
            }
        }
    }
}
