package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.notes.fragments.ListNotesFragment
import com.example.notes.model.AppDatabase

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "notebase").build()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ListNotesFragment())
            .commit()
    }
}