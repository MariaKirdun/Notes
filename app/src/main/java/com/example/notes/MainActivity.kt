package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.notes.fragments.ListNotesFragment
import com.example.notes.model.AppDatabase

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : MainActivity =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "notebase").build()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ListNotesFragment())
            .commit()

    }
}