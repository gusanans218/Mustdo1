package com.example.mustdo.di

import com.example.mustdo.data.repository.TestToDoRepository
import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.domain.todo.GetToDoListUseCase
import com.example.mustdo.domain.todo.InsertToDoListUseCase
import com.example.mustdo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appTestModule = module {
    //viewModel
    viewModel{ ListViewModel(get()) }
    //UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }

    //Repository
    single<ToDoRepository> { TestToDoRepository() }

}