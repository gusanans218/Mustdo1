package com.example.mustdo.data.repository.local.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mustdo.data.repository.entity.ToDoEntity

@Database(
    entities = [ToDoEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ToDoDatabase:RoomDatabase() {
    companion object{
        const val DB_NAME = "ToDoDataBase.db"
    }
    abstract fun toDoDao():ToDoDao

}