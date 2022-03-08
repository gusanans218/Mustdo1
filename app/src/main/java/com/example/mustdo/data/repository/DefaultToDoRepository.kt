package com.example.mustdo.data.repository

import com.example.mustdo.data.repository.entity.ToDoEntity

class DefaultToDoRepository:ToDoRepository {

    override suspend fun getToDoList(): List<ToDoEntity> {

    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {


    }
}