package com.example.mynoteapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.R
import com.example.mynoteapp.databinding.UpdateTaskCellBinding
import com.example.mynoteapp.model.Task

class UpdateTaskAdapter(var tasks: ArrayList<Task>) :
    RecyclerView.Adapter<UpdateTaskAdapter.UpdateTaskHolder>() {

    class UpdateTaskHolder (val binding: UpdateTaskCellBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpdateTaskHolder {
        return UpdateTaskHolder(
            UpdateTaskCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UpdateTaskHolder, position: Int) {
        val task = tasks[position]
        holder.binding.apply {
            taskText.setText(task.taskText)

            if (task.taskIsComplete) {
                doneTaskCheck.setImageResource(R.drawable.check)
            } else {
                doneTaskCheck.setImageResource(0)
            }

        }

        holder.binding.doneTaskCheck.setOnClickListener {
            if (task.taskIsComplete) {
                holder.binding.doneTaskCheck.setImageResource(0)
                task.taskIsComplete = false
            } else {
                holder.binding.doneTaskCheck.setImageResource(R.drawable.check)
                task.taskIsComplete= true
            }
        }

        holder.binding.deleteTaskButton.setOnClickListener {
            tasks.remove(task)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = tasks.size

    fun update(newList: ArrayList<Task>){
        tasks = newList
        notifyDataSetChanged()
    }
}