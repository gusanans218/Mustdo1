package com.example.mustdo.data.repository

import com.example.mustdo.data.repository.entity.ToDoEntity

class TestToDoRepository:ToDoRepository {

    private val toDoList:MutableList<ToDoEntity> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.toDoList.addAll(toDoList)

    }
}