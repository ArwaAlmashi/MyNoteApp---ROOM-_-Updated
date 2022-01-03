package com.example.mynoteapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.databinding.AddTaskCellBinding
import com.example.mynoteapp.model.Task



class AddTaskAdapter(var tasks: ArrayList<String>): RecyclerView.Adapter<AddTaskAdapter.AddTaskHolder> () {
    class AddTaskHolder (val binding: AddTaskCellBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddTaskAdapter.AddTaskHolder {
        return AddTaskHolder(
            AddTaskCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AddTaskAdapter.AddTaskHolder, position: Int) {
    }

    override fun getItemCount(): Int = tasks.size

    fun update(newList: ArrayList<String>){
        tasks = newList
        notifyDataSetChanged()
    }

}