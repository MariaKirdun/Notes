package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.model.Note
import com.example.notes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.details_fragment.*
import java.util.*

class DetailsFragment(val note: Note?) : Fragment() {

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider.AndroidViewModelFactory(this.activity!!.application).create(
            NotesViewModel::class.java
        )

        note?.let {
            title_node_details.setText(note.name)
            text_node_details.setText(note.text)
        }
            save_button.setOnClickListener {
                when (note) {
                    null -> viewModel.insert(getNode())
                    else -> viewModel.update(getNode())
                }
                activity?.supportFragmentManager?.popBackStack()
            }
        }

    fun getNode(): Note {
        val name = title_node_details.text.toString()
        val text = text_node_details.text.toString()
        return when(note) {
            null -> Note(name = name, text = text, date = Date(), color = null)
            else -> Note(note.uid, name = name, text = text, date = Date(), color = null)
        }
    }

}