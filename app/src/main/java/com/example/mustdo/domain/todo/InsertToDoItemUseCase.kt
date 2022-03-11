package com.example.mustdo.domain.todo

import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.UseCase

internal class InsertToDoItemUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {
    suspend operator fun invoke(toDoItem:ToDoEntity){
        return toDoRepository.insertToDoItem(toDoItem)
    }
}