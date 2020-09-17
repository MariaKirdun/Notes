package com.example.notes.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.Note
import kotlinx.android.synthetic.main.note_item_layout.view.*


class NodesAdapter :RecyclerView.Adapter<NodesAdapter.NodesViewHolder>(){

    private var dataset: List<Note> = listOf()
    var listener : View.OnClickListener? = null
    var position : Int = 0

    class NodesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodesViewHolder {
        return NodesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NodesViewHolder, position: Int) {
        val note = dataset[position]
        holder.itemView.name_note.text = note.name
        holder.itemView.text_note.text = note.text
        holder.itemView.pin_button.text = if(!note.isPinned) "Pin" else "Unpin"
        holder.itemView.pin_button.setOnClickListener {
            this.position = position
            listener?.onClick(it)
        }
        holder.itemView.setOnClickListener {
            this.position = position
            listener?.onClick(it)
        }
    }

    fun setNotes(notes: List<Note>) {
        this.dataset = notes
        dataset = dataset.sortedByDescending { it.isPinned }
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.size

    fun getItem(position: Int) = dataset[position]
}