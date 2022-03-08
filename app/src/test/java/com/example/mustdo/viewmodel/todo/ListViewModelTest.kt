package com.example.mustdo.viewmodel.todo

import com.example.mustdo.presentation.list.ListViewModel
import org.junit.Before
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    //필요한 Usecase를
    //1. InsertToDoListUseCase
    //2. GetToDoItemUseCase

    @Before
    fun init(){
        initData()
    }
    private fun initData() =
}