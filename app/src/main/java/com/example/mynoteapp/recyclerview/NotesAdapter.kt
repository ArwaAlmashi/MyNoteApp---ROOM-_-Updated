package com.example.mynoteapp.recyclerview

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.activity.UpdateAndDeleteNoteActivity
import com.example.mynoteapp.databinding.NotesCardBinding
import com.example.mynoteapp.model.NoteModel
import com.example.mynoteapp.model.Task
import com.google.gson.GsonBuilder

class NotesAdapter(var noteList: List<NoteModel>, var context: Context) :
    RecyclerView.Adapter<NotesAdapter.AllNotesHolder>() {
    class AllNotesHolder(val binding: NotesCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNotesHolder {
        return AllNotesHolder(
            NotesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: AllNotesHolder, position: Int) {
        val note = noteList[position]
        val tasksList = convertStringToObjList(note.tasks) as List<Task>
        holder.binding.apply {
            titleNoteTv.text = note.title
            progressValue(tasksList, tasksProgressbar)
        }
        holder.binding.allNotesCard.setOnClickListener {
            val  intent = Intent(context, UpdateAndDeleteNoteActivity::class.java )
            intent.putExtra("note", note)
            context.startActivity(intent)
        }

    }

    override fun getItemCount() = noteList.size

    private fun convertStringToObjList(tasksString: String): List<Task> {
        val gson = GsonBuilder().create()
        val model = gson.fromJson(tasksString, Array<Task>::class.java).toList()
        return model
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun progressValue(tasks: List<Task>, progressBar: ProgressBar) {
        var progressBarAmount = 0
        if (tasks.size > 0 ) {
            progressBar.max = tasks.size
            for (task in tasks) {
                if (task.taskIsComplete) {
                    progressBarAmount++
                }
            }
            progressBar.setProgress(progressBarAmount, true)
        }
    }

}