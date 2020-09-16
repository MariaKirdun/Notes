package com.example.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "text") val text: String = "",
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "color") val color: String?
)
