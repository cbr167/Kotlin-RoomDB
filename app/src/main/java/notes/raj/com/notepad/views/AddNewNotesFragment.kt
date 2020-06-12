package notes.raj.com.notepad.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_new_notes.*

import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.graphics.drawable.Drawable
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import notes.raj.com.notepad.R
import notes.raj.com.notepad.database.Note
import notes.raj.com.notepad.viewmodel.NoteViewModel


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddNewNotesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddNewNotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNewNotesFragment : Fragment() {

    private var noteViewModel: NoteViewModel? = null
    lateinit var mContext:Context

    var rowID: Int = -1
    var desc: String? = null
    var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //change the title bar
        noteViewModel = activity?.let { ViewModelProviders.of(it).get(NoteViewModel::class.java) };
        return inflater!!.inflate(R.layout.fragment_add_new_notes, container, false)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context!!

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.add_new_notes, menu)
        changeIconColor(menu?.findItem(R.id.save)?.icon!!)
        changeIconColor(menu.findItem(R.id.back)?.icon!!)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (getArguments() != null) {
            activity?.setTitle("Edit Notes")
            rowID = getArguments()!!.getInt("id")
            titleEditTextNew.setText(getArguments()?.getString("title"))
            descEditTextNew.setText(getArguments()?.getString("desc"))
        }else{
            activity?.setTitle("Add Notes")
        }
    }

    fun changeIconColor(icon: Drawable) {
        var drawableIcon = icon
        drawableIcon = drawableIcon.let { DrawableCompat.wrap(it) }
        drawableIcon?.let { DrawableCompat.setTint(it, ContextCompat.getColor(mContext, R.color.white)) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.save -> {
                saveNote()

                return true
            }
            R.id.back -> {
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.container_a, HomeScreenFragment())
                transaction?.commit()
                if (activity?.currentFocus != null) {
                    val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, 0)
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        var title = titleEditTextNew.text.toString()
        var description = descEditTextNew.text.toString()
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(context, "Please Enter Title and Description", Toast.LENGTH_SHORT).show()
            return
        } else {
            val note = Note(title, description)
            if (rowID != -1) {
                note.id = rowID
                noteViewModel?.update(note)
            } else {
                noteViewModel?.insert(note)
            }
            Toast.makeText(context, "Notes Saved", Toast.LENGTH_SHORT).show()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container_a, HomeScreenFragment())
            transaction?.commit()
        }
        if (activity?.currentFocus != null) {
            val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }
    }

    override fun onDetach() {
        super.onDetach()

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}