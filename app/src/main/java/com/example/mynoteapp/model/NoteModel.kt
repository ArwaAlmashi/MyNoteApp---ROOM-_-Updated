package com.example.mynoteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "NoteModel")
data class NoteModel (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "Title") var title: String,
    @ColumnInfo(name = "Tasks") var tasks: String,
    @ColumnInfo(name = "IsCompleted") var isComplete: Boolean = false,
    @ColumnInfo(name = "Date") val date: String
) :Serializable