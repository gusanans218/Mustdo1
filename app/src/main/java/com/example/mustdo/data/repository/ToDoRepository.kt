package com.example.mustdo.data.repository

import com.example.mustdo.data.repository.entity.ToDoEntity

// 1. insertToDoList
// 2. getToDoList
// 3. updateToDoItem

interface ToDoRepository {

    suspend fun getToDoList():List<ToDoEntity>

    suspend fun insertToDoItem(toDoItem:ToDoEntity)

    suspend fun insertToDoList(toDoList:List<ToDoEntity>)

    suspend fun updateToDoItem(toDoItem:ToDoEntity):Boolean

    suspend fun getToDoItem(itemId:Long):ToDoEntity?

    suspend fun deleteToDoItem(id:Long):Boolean

    suspend fun deleteAll()
}