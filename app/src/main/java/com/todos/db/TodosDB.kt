package com.todos.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.todos.pojo.Todo

 @Database(entities = [Todo::class], version =1, exportSchema = false)
abstract class TodosDB: RoomDatabase() {
    abstract fun todoDao(): TodoDAO
}