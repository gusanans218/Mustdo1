package com.example.mustdo.viewmodel.todo

import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.todo.InsertToDoItemUseCase
import com.example.mustdo.presentation.list.ListViewModel
import com.example.mustdo.presentation.list.ToDoListState
import com.example.mustdo.presentation.list.detail.DetailMode
import com.example.mustdo.presentation.list.detail.DetailViewModel
import com.example.mustdo.presentation.list.detail.ToDoDetailState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import java.lang.Exception

//[DetailViewModel]을 테스트 하기 위한 Unit Test Class
//1. initData()
//2. test viewModel fetch()
//3. test delete todo
//4. test update todo

@ExperimentalCoroutinesApi
internal class DetailViewModelTest:ViewModelTest() {

    private val id = 1L
    private val detailViewModel by inject<DetailViewModel>{ parametersOf(DetailMode.DETAIL,id)  }
    private val listViewModel by inject<ListViewModel>()
    private val insertToDoItemUseCase:InsertToDoItemUseCase by inject()
    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )
    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoItemUseCase(todo )//데이터 초기화
    }

    @Test
    fun `test viewModel fetch()`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )

        )
    }
    @Test
    fun `test delete todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        detailViewModel.deleteTodo()

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Delete
            )
        )
        val listTestObservable = listViewModel.toDoListLiveData.test()
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
            ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )
    }
}