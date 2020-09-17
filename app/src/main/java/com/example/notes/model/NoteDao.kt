package com.example.notes.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

    @Insert
    fun insert( note: Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)
}