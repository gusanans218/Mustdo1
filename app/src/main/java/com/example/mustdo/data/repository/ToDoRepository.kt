package com.example.mustdo.data.repository

import com.example.mustdo.data.repository.entity.ToDoEntity

// 1. insertToDoList
// 2. getToDoList

interface ToDoRepository {

    suspend fun getToDoList():List<ToDoEntity>

    suspend fun insertToDoList(toDoList:List<ToDoEntity>)

}