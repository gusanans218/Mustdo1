package com.example.mustdo.presentation.list.detail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.mustdo.domain.todo.DeleteToDoItemUseCase
import com.example.mustdo.domain.todo.GetToDoItemUseCase
import com.example.mustdo.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.security.PrivateKey

internal class DetailViewModel(
    var detailMode: DetailMode,
    var id: Long = -1,
    private val getToDoItemUseCase:GetToDoItemUseCase,
    private val deleteToDoItemUseCase: DeleteToDoItemUseCase
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

    fun deleteTodo() = viewModelScope.launch {
        _toDoDetailLiveData.postValue(ToDoDetailState.Loading)
        try {
            if(deleteToDoItemUseCase(id) == true){
                _toDoDetailLiveData.postValue(ToDoDetailState.Delete)

            }else{
                _toDoDetailLiveData.postValue(ToDoDetailState.Error)

            }
        }catch (e:Exception){
            _toDoDetailLiveData.postValue(ToDoDetailState.Error)

        }
        _toDoDetailLiveData.postValue(ToDoDetailState.Error)

    }
}