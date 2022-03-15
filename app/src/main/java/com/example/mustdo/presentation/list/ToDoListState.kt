package com.example.mustdo.presentation.list

import com.example.mustdo.data.repository.entity.ToDoEntity

sealed class ToDoListState {
    object UnInitialized: ToDoListState()
    object Loading: ToDoListState()

    data class Success(
        val toDoList: List<ToDoEntity>
    ): ToDoListState()

    object Error: ToDoListState()

}
