package com.example.notes.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.Note

class NodesAdapter(private val dataset: List<Note>) :RecyclerView.Adapter<NodesAdapter.NodesViewHolder>(){
    
    class NodesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodesViewHolder {
        return NodesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout, parent)
        )
    }

    override fun onBindViewHolder(holder: NodesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}