package notesapp.raj.com.notes.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import notesapp.raj.com.notes.R
import notesapp.raj.com.notes.database.Note
import notesapp.raj.com.notes.interfaces.OnItemClickListener

import java.util.ArrayList

/**
 * Created by bhagy on 4/13/2020.
 */

class RecyclerNoteAdapter(var onItemInterface: OnItemClickListener) : RecyclerView.Adapter<RecyclerNoteAdapter.NotesViewHolder>() {

    private var notesList: List<Note> = ArrayList()
  //  val onItemInterface = HomeScreenFragment()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        holder.descText.text = note.description
        holder.titleText.text = note.title

    }


    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setNotes(list: List<Note>) {
        this.notesList = list
        notifyDataSetChanged()
    }
    fun getNoteAt(position:Int): Note {
        return notesList.get(position)
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val descText: TextView
         val titleText: TextView

        init {
            titleText = itemView.findViewById(R.id.titleText)
            descText = itemView.findViewById(R.id.descText)
            itemView.setOnClickListener(View.OnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION) {
                    onItemInterface.onRowClick(notesList.get(adapterPosition))
                }
            });


        }


    }
}
