package com.todos.repository

import androidx.lifecycle.LiveData
import com.todos.db.TodoDAO
import com.todos.network.TodosApiService
import com.todos.pojo.Todo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HomeRespoitory @Inject constructor( var todosApiService: TodosApiService, var todoDao: TodoDAO) {

    fun getTodosList(): Observable<ArrayList<Todo>> = todosApiService.getTodos()

    fun addToFavs(todo: Todo) = todoDao.addTodo(todo)

    fun removeFromFavs(id: Int) = todoDao.deleteTodo(id)

    fun getAllFavs(): LiveData<List<Todo>> {
        return todoDao.getAllTodos()
    }
}