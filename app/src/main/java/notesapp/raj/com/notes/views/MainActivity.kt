package notesapp.raj.com.notes.views

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import notesapp.raj.com.notes.R
import notesapp.raj.com.notes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private var test: Int = 0

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startAsyncWork()
        // setTitle("Add Note");
        supportFragmentManager.beginTransaction().add(R.id.container_a, HomeScreenFragment()).commit()


    }
    private fun startAsyncWork() {
        val work = Runnable {
            test = 1 // comment this line to pass the test
            SystemClock.sleep(20000)
        }
        Thread(work).start()
    }
}
