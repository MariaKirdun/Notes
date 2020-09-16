package com.example.notes.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Task(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "state") val state: Boolean
)