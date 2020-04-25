package notesapp.raj.com.notes.views

import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Canvas
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.*
import android.widget.Toast
import notesapp.raj.com.notes.R
import notesapp.raj.com.notes.adapter.RecyclerNoteAdapter
import notesapp.raj.com.notes.database.Note
import notesapp.raj.com.notes.interfaces.OnItemClickListener
import notesapp.raj.com.notes.viewmodel.NoteViewModel


class HomeScreenFragment : Fragment(), OnItemClickListener {

    override fun onRowClick(note: Note) {
        val fragment = AddNewNotesFragment()
        val args = Bundle()
        args.putInt("id", note.id)
        args.putString("desc",note.description)
        args.putString("title",note.title)
        fragment.setArguments(args)
        fragmentManager?.beginTransaction()?.addToBackStack("HomeFragment")?.replace(R.id.container_a, fragment)?.commit()
       // Toast.makeText(mContext,"Note Clicked", Toast.LENGTH_SHORT).show()
        Log.d("sdgsg","ageg")
    }


    private var noteViewModel: NoteViewModel? = null
    lateinit var mContext:Context



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater?.inflate(R.layout.fragment_home_screen, container, false)
        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.noteRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)
       val noteAdapter = RecyclerNoteAdapter(this)
        recyclerView?.adapter = noteAdapter
        noteViewModel = activity?.let { ViewModelProviders.of(it).get(NoteViewModel::class.java) };
        noteViewModel?.allNotes?.observe(this, Observer { notes -> notes?.let { it ->
            val reversedNotes = it.asReversed()
            noteAdapter.setNotes(reversedNotes) } })
        val fab = rootView?.findViewById(R.id.fabAdd) as FloatingActionButton

        fab.setOnClickListener{view->
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container_a, AddNewNotesFragment())
            transaction?.addToBackStack("fdf")
            transaction?.commit()
        }
        var mIth = ItemTouchHelper(
                object : ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(recyclerView: RecyclerView,
                                        viewHolder: ViewHolder, target: ViewHolder): Boolean {

                        return false// true if moved, false otherwise
                    }

                    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                        noteViewModel?.delete(noteAdapter.getNoteAt(viewHolder.adapterPosition))
                        Toast.makeText(context,"Note Deleted", Toast.LENGTH_SHORT).show()
                        // remove from adapter
                    }


                }).attachToRecyclerView(recyclerView)

        return rootView

    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
      menu?.clear()
        inflater?.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
        activity?.setTitle("Add Notes")
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId)
        {
            R.id.delete_all_notes -> {
                noteViewModel?.deleteAllNotes();
                Toast.makeText(context,"All Notes Deleted", Toast.LENGTH_SHORT).show()
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("rajj","wdwfw")
        this.mContext = context!!


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

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): HomeScreenFragment {
            val fragment = HomeScreenFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
