package com.example.mustdo.presentation.list.detail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.todo.DeleteToDoItemUseCase
import com.example.mustdo.domain.todo.GetToDoItemUseCase
import com.example.mustdo.domain.todo.InsertToDoItemUseCase
import com.example.mustdo.domain.todo.UpdateToDoUseCase
import com.example.mustdo.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

internal class DetailViewModel(
    var detailMode: DetailMode,
    var id: Long = -1,
    private val getToDoItemUseCase:GetToDoItemUseCase,
    private val deleteToDoItemUseCase: DeleteToDoItemUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val insertToDoUseCase: InsertToDoItemUseCase
):BaseViewModel() {

    private var _toDoDetailLiveData= MutableLiveData<ToDoDetailState>(ToDoDetailState.UnInitialized)
    val toDoDetailLiveData:LiveData<ToDoDetailState> = _toDoDetailLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        when (detailMode) {
            DetailMode.WRITE -> {
                //TODO 나중에 작성모드로 상세화면 진입 로직 처리
            }
            DetailMode.DETAIL -> {
                _toDoDetailLiveData.postValue(ToDoDetailState.Loading)
                try {
                    getToDoItemUseCase(id)?.let {
                        _toDoDetailLiveData.postValue(ToDoDetailState.Success(it))
                    }?:kotlin.run {
                        _toDoDetailLiveData.postValue(ToDoDetailState.Error)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    _toDoDetailLiveData.postValue(ToDoDetailState.Error)

                }
            }
        }
    }

    fun deleteToDo() = viewModelScope.launch {
        _toDoDetailLiveData.postValue(ToDoDetailState.Loading)
        try {
            deleteToDoItemUseCase(id)
            _toDoDetailLiveData.postValue(ToDoDetailState.Delete)
        } catch (e: Exception) {
            e.printStackTrace()
            _toDoDetailLiveData.postValue(ToDoDetailState.Error)
        }
    }
    fun writeToDo(title: String, description: String) = viewModelScope.launch {
        _toDoDetailLiveData.postValue(ToDoDetailState.Loading)
        when (detailMode) {
            DetailMode.WRITE -> {
                try {
                    val toDoEntity = ToDoEntity(title = title, description =  description)
                    id = insertToDoUseCase(toDoEntity)
                    _toDoDetailLiveData.postValue(ToDoDetailState.Success(toDoEntity))
                    detailMode = DetailMode.DETAIL
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoDetailLiveData.postValue(ToDoDetailState.Error)
                }
            }
            DetailMode.DETAIL -> {
                try {
                    getToDoItemUseCase(id)?.let {
                        val updateToDoEntity = it.copy(title = title, description = description)
                        updateToDoUseCase(updateToDoEntity)
                        _toDoDetailLiveData.postValue(ToDoDetailState.Success(updateToDoEntity))
                    } ?: kotlin.run {
                        _toDoDetailLiveData.postValue(ToDoDetailState.Error)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoDetailLiveData.postValue(ToDoDetailState.Error)
                }
            }
        }
    }

}