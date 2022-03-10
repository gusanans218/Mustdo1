package com.example.mustdo.domain.todo

import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.UseCase

internal class UpdateToDoListUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {
    suspend operator fun invoke(toDoEntity: ToDoEntity): Boolean {
        return toDoRepository.updateToDoItem(toDoEntity)
    }
}