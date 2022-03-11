package com.example.mustdo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.todo.DeleteAllToDoItemUseCase
import com.example.mustdo.domain.todo.GetToDoListUseCase
import com.example.mustdo.domain.todo.UpdateToDoListUseCase
import com.example.mustdo.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//필요한 UseCase
//1. GetToDoListUseCase
//2. UpdateToDoUseCase
//3. DeleteAllToDoItemUseCase

internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoListUseCase: UpdateToDoListUseCase,
    private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase
): BaseViewModel() {
    private var _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val toDoListLiveData: LiveData<ToDoListState> = _toDoListLiveData

    override fun fetchData(): Job = viewModelScope.launch{
        _toDoListLiveData.postValue(ToDoListState.Loading)
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }
    fun updateEntity(toDoEntity: ToDoEntity)=viewModelScope.launch{
        updateToDoListUseCase(toDoEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        deleteAllToDoItemUseCase()
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }

}