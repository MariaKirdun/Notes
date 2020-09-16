package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.notes.R
import com.example.notes.model.AppDatabase
import com.example.notes.viewmodel.ListNotesViewModel
import kotlinx.android.synthetic.main.notes_fragment.*

class ListNotesFragment : Fragment() {

    private lateinit var viewModel: ListNotesViewModel
    private var adapter = NodesAdapter (listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ListNotesViewModel(Room.databaseBuilder(activity!!.applicationContext, AppDatabase::class.java, "notebase").build().noteDao())
        viewModel.addNotesIntoBase()

        viewModel.notes.observe(this) {
            adapter = NodesAdapter(it)
        }
    }

}