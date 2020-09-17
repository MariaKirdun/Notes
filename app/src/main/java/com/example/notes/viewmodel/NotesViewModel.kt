package com.example.notes.viewmodel

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.AppDatabase
import com.example.notes.model.Note
import com.example.notes.model.NoteDao
import com.example.notes.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.coroutineContext

class NotesViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: Repository

    val notes: LiveData<List<Note>>

    init {
        val noteDao = AppDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = Repository(noteDao)
        notes = repository.allNotes
    }

    fun insert(note: Note){
       viewModelScope.launch(Dispatchers.IO) {
           repository.insert(note)
       }
    }

    fun delete(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(note)
        }
    }

}