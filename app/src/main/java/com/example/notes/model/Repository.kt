package com.example.notes.model

import androidx.lifecycle.LiveData

class Repository (private val nodeDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = nodeDao.getAll()

    suspend fun insert(note: Note) {
        nodeDao.insert(note)
    }

    suspend fun delete(note: Note) {
        nodeDao.delete(note)
    }

    suspend fun update(note: Note) {
        nodeDao.update(note)
    }

}