package com.todos.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.todos.pojo.Todo

@Dao
interface TodoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTodo(todo: Todo)

    @Query("delete from todos where id = :id")
    fun deleteTodo(id: Int)

    @Query("select * from todos")
    fun getAllTodos(): LiveData<List<Todo>>
}