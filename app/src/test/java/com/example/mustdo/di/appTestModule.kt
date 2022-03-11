package com.example.mustdo.di

import com.example.mustdo.data.repository.TestToDoRepository
import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.domain.todo.*
import com.example.mustdo.presentation.list.ListViewModel
import com.example.mustdo.presentation.list.detail.DetailMode
import com.example.mustdo.presentation.list.detail.DetailViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appTestModule = module {
    //viewModel
    viewModel{ ListViewModel(get(),get(),get()) }
    viewModel{(detailMode: DetailMode,id:Long) ->
        DetailViewModel(
            detailMode = detailMode,
            id = id,
            get(),get(),get(),get()
        )
    }
    //UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }

    //Repository
    single<ToDoRepository> { TestToDoRepository() }

}