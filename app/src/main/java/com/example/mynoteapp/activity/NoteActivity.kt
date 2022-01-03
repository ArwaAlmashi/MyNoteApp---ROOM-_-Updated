package com.example.mynoteapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.database.NoteDatabase
import com.example.mynoteapp.databinding.ActivityNoteBinding
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.model.NoteModel
import com.example.mynoteapp.recyclerview.NotesAdapter
import com.example.mynoteapp.setFullScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    private lateinit var notesRV: RecyclerView
    private lateinit var notesAdapter: NotesAdapter

    private var notes = listOf<NoteModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set UI
        setFullScreen(window)
        lightStatueBar(window)

        // Get All Note (ROOM)
        CoroutineScope(Dispatchers.IO).launch {
            notes = NoteDatabase.getInstance(applicationContext).NoteDao()
                .getAllNotes()
            println("Room Notes")
            println(notes)

            runOnUiThread {
                if (notes.isNotEmpty()) {
                    setRecyclerview()
                    notesRV = binding.allNotesRecyclerview
                    notesAdapter = NotesAdapter(notes, this@NoteActivity)
                    notesRV.adapter = notesAdapter
                    notesAdapter.notifyDataSetChanged()
                }
            }

        }

        // Go to Add Note
        binding.addIcon.setOnClickListener {
            val intent = Intent(this, AddNewNoteActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setRecyclerview() {
        notesRV = binding.allNotesRecyclerview
        notesAdapter = NotesAdapter(notes, this)
        notesRV.adapter = notesAdapter
    }

}