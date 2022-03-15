package com.example.mustdo.di

import android.content.Context
import androidx.room.Room
import com.example.mustdo.data.repository.DefaultToDoRepository
import com.example.mustdo.data.repository.ToDoRepository
import com.example.mustdo.data.repository.local.db.dao.ToDoDatabase
import com.example.mustdo.domain.todo.*
import com.example.mustdo.domain.todo.DeleteAllToDoItemUseCase
import com.example.mustdo.domain.todo.DeleteToDoItemUseCase
import com.example.mustdo.domain.todo.GetToDoItemUseCase
import com.example.mustdo.domain.todo.GetToDoListUseCase
import com.example.mustdo.domain.todo.InsertToDoItemUseCase
import com.example.mustdo.domain.todo.InsertToDoListUseCase
import com.example.mustdo.domain.todo.UpdateToDoUseCase
import com.example.mustdo.presentation.list.ListViewModel
import com.example.mustdo.presentation.list.detail.DetailMode
import com.example.mustdo.presentation.list.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }

    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get(), get()) }

}

internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()