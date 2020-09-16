package com.example.notes.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import com.example.notes.model.Note
import com.example.notes.model.NoteDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.coroutineContext

class ListNotesViewModel(private val database: NoteDao) : ViewModel() {

    val notes = database.getAll()

    fun addNotesIntoBase(){
        GlobalScope.launch {
            database.insertAll(
                Note(1,"first", "text", Date(), null),
                Note(2,"second", "text", Date(), null)
            )
        }
    }

}