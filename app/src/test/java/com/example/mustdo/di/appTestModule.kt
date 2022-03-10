package com.example.mustdo.di

import com.example.mustdo.data.repository.TestToDoRepository
import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.domain.todo.DeleteAllToDoItemUseCase
import com.example.mustdo.domain.todo.GetToDoListUseCase
import com.example.mustdo.domain.todo.InsertToDoListUseCase
import com.example.mustdo.domain.todo.UpdateToDoListUseCase
import com.example.mustdo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appTestModule = module {
    //viewModel
    viewModel{ ListViewModel(get(),get()) }
    //UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoListUseCase(get()) }
    factory { GetToDoListUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }

    //Repository
    single<ToDoRepository> { TestToDoRepository() }

}