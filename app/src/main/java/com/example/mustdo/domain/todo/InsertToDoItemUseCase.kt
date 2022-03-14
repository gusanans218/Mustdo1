package com.example.mustdo.domain.todo

import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.UseCase

class InsertToDoItemUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(toDoEntity: ToDoEntity): Long {
        return toDoRepository.insertToDoItem(toDoEntity)
    }

}