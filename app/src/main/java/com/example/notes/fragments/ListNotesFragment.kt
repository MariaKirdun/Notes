package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.Note
import com.example.notes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.note_item_layout.view.*
import kotlinx.android.synthetic.main.notes_fragment.*
import java.util.*


class ListNotesFragment : Fragment() {

    private lateinit var viewModel: NotesViewModel
    private val adapter = NodesAdapter()

    private var notesList: List<Note> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider.AndroidViewModelFactory(this.activity!!.application).create(
            NotesViewModel::class.java
        )


        viewModel.notes.observe(this, androidx.lifecycle.Observer { notes ->
            notes.let {
                adapter.setNotes(it)
                notesList = it
            }
        })

        notes_list.adapter = adapter
        notes_list.layoutManager = LinearLayoutManager(this.context)
        adapter.listener = View.OnClickListener {
            when(it.id) {
                R.id.pin_button ->
                    Toast.makeText(this.context, "pin", Toast.LENGTH_SHORT).show()
                else ->
                    openDetails(adapter.getItem(adapter.position))
            }
        }
        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(notes_list)

        add_note_button.setOnClickListener {
            openDetails(null)
        }

        search_edit_text.doOnTextChanged { text, start, count, after ->
            adapter.setNotes(sortByName(text.toString()))
        }
    }

    private fun sortByName(str: String) = notesList.filter {
        it.name.contains(str, true) ||
        it.text.contains(str,true)
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.adapterPosition
            viewModel.delete(adapter.getItem(position))
        }
    }

    private fun openDetails(note: Note?){
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment(note))
                .addToBackStack(null)
                .commit()
        }
    }

}