package com.example.notes.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

    @Insert
    fun insert( note: Note)

    @Delete
    fun delete(note: Note)
}