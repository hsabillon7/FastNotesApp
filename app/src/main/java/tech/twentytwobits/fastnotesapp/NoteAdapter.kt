package tech.twentytwobits.fastnotesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import tech.twentytwobits.fastnotesapp.NotesDao.Note

class NoteAdapter(var noteList: List<Note> = ArrayList<Note>()): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private lateinit var onNoteItemClickListener: OnNoteItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_notes_items, parent, false)

        return ViewHolder(view, noteList)
    }

    override fun getItemCount(): Int {
        return noteList.count()
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.view.setOnClickListener {
            onNoteItemClickListener.onNoteItemClickListener(noteList[position])
        }
        holder.view.setOnLongClickListener {
            onNoteItemClickListener.onNoteItemLongClickListener(noteList[position])
            true
        }
        holder.onBindView(position)
    }

    class ViewHolder(val view: View, val noteList: List<Note>): RecyclerView.ViewHolder(view) {
        fun onBindView(position: Int) {
            view.findViewById<TextView>(R.id.textViewTitle).text = noteList[position].title
            view.findViewById<TextView>(R.id.textViewFirstLetter).text = noteList[position].title.first().toUpperCase().toString()
            view.findViewById<ImageView>(R.id.imageViewPriority).setImageResource(getImagePriority(noteList[position].priority))
        }

        private fun getImagePriority(priority: Int): Int
        = if (priority == 1) R.drawable.low_priority else if (priority == 2) R.drawable.medium_priority else R.drawable.high_priority
    }

    fun setNoteItemClickListener(onNoteItemClickListener: OnNoteItemClickListener) {
        this.onNoteItemClickListener = onNoteItemClickListener
    }

    interface OnNoteItemClickListener {
        fun onNoteItemClickListener(note: Note)
        fun onNoteItemLongClickListener(note: Note)
    }
}