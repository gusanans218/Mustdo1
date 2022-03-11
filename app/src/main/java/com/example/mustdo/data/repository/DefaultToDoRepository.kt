package com.example.mustdo.data.repository

import com.example.mustdo.data.repository.entity.ToDoEntity

class DefaultToDoRepository:ToDoRepository {

    override suspend fun getToDoList(): List<ToDoEntity> {
        return getToDoList()
    }

    override suspend fun insertToDoItem(toDoItem: ToDoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {


    }

    override suspend fun updateToDoItem(toDoItem: ToDoEntity):Boolean {
        TODO()
    }

    override suspend fun getToDoItem(itemId: Long): ToDoEntity? {
        TODO()
    }

    override suspend fun deleteToDoItem(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}