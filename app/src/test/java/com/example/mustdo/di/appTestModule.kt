package com.example.mustdo.di

import com.example.mustdo.data.repository.TestToDoRepository
import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.domain.todo.*
import com.example.mustdo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appTestModule = module {
    //viewModel
    viewModel{ ListViewModel(get(),get(),get()) }
    //UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }

    //Repository
    single<ToDoRepository> { TestToDoRepository() }

}