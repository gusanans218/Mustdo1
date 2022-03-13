package com.example.mustdo.data.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity (
    @PrimaryKey(autoGenerate = true )
    val id : Long = 0,
    val title:String,
    val description : String,
    val hasCompleted:Boolean=false
    )