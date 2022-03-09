package com.example.mustdo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.todo.GetToDoListUseCase

//필요한 UseCase
//1. GetToDoListUseCase
//2. UpdateToDoUseCase
//3. DeleteAllToDoItemUseCase

internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase
): ViewModel() {
    private var _toDoListLiveData = MutableLiveData<List<ToDoEntity>>(listOf())
    val todoListLiveData:LiveData<List<ToDoEntity>> = _toDoListLiveData

    fun fetchData(){
        
    }

}