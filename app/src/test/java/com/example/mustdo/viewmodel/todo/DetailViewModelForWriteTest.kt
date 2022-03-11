package com.example.mustdo.viewmodel.todo

import com.example.mustdo.data.repository.entity.ToDoEntity
import com.example.mustdo.presentation.list.ListViewModel
import com.example.mustdo.presentation.list.detail.DetailMode
import com.example.mustdo.presentation.list.detail.DetailViewModel
import com.example.mustdo.presentation.list.detail.ToDoDetailState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject



//[DetailViewModel]을 테스트 하기 위한 Unit Test Class
//2. test viewModel fetch()
//4. test insert todo

@ExperimentalCoroutinesApi
internal class DetailViewModelForWriteTest:ViewModelTest(){

    private val id = 0L

    private val detailViewModel by inject<DetailViewModel>{ parametersOf(DetailMode.WRITE,id)  }
    private val listViewModel by inject<ListViewModel>()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )
    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.todoDetailLiveData.test()
        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Write
            )

        )
    }
}