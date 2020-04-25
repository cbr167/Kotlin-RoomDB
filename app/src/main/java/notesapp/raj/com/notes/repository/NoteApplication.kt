package notesapp.raj.com.notes.repository

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 * Created by bhagy on 4/22/2020.
 */
class NoteApplication: Application() {

    lateinit var refWatcher:RefWatcher

    override fun onCreate() {
        super.onCreate()
        refWatcher = LeakCanary.install(this)
    }
}