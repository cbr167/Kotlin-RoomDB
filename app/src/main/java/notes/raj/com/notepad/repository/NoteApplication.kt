package notes.raj.com.notepad.repository

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
fun main(args:Array<String>){
    val a:String?=null
    val b:String = "sfw"
    print(a==b)
}
