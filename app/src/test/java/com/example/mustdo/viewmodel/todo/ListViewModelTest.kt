package com.example.mustdo.viewmodel.todo

import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.domain.todo.GetToDoItemUseCase
import com.example.mustdo.domain.todo.GetToDoListUseCase
import com.example.mustdo.domain.todo.InsertToDoListUseCase
import com.example.mustdo.presentation.list.ListViewModel
import com.example.mustdo.presentation.list.ToDoListState
import dalvik.annotation.TestTarget
import org.junit.Before
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.test.inject



//[ListViewModel]을 테스트하기 위한 Unit Test Class
// 1. initData()
// 2. test viewModel fetch
// 3. test Item Update
// 4. test Item Delete All



@ExperimentalCoroutinesApi
internal class ListViewModelTest:ViewModelTest() {

    private val viewModel: ListViewModel by inject()
    private val insertToDoListUseCase: InsertToDoListUseCase by inject()
    private val getToDoItemUseCase: GetToDoItemUseCase by inject()
    private val mockList = (0 until 10).map {
        ToDoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }
    //필요한 Usecase를
    //1. InsertToDoListUseCase
    //2. GetToDoItemUseCase

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoListUseCase(mockList)//데이터 초기화
    }

    //데이터 불러오는 테스트
    //Test 1. 입력된 데이터를 불러와서 검증한다.
    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = viewModel.toDoListLiveData.test()

        viewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(mockList)
            )
        )

    }

    // Test : 데이터를 업데이트 했을 때 잘 반영되는가
    @Test
    fun `test Item Update`(): Unit = runBlockingTest {
        val todo = ToDoEntity(
            id = 1,
            title = "title 1",
            description = "description 1",
            hasCompleted = true
        )
        viewModel.updateEntity(todo)
        assert(getToDoItemUseCase(todo.id)?.hasCompleted ?: false == todo.hasCompleted)
    }

    //Test : 데이터를 다 날렸을 때 빈 상태로 보여지는가
    @Test
    fun `test Item Delete All`(): Unit = runBlockingTest {
        val testObservable = viewModel.toDoListLiveData.test()
        viewModel.deleteAll()
        testObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf())
            )
        )
    }
}
