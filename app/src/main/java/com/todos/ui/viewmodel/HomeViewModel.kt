package com.todos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.todos.pojo.Todo
import com.todos.repository.HomeRespoitory
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private var homeRespoitory: HomeRespoitory): ViewModel() {
    private var todosLiveData: MutableLiveData<ArrayList<Todo>> = MutableLiveData()
    private lateinit var favLiveData: LiveData<List<Todo>>

    fun getTodosLiveData(): MutableLiveData<ArrayList<Todo>>{
        return todosLiveData
    }

    fun getFavLiveDate() = favLiveData

    fun getTodos(){
        var todosList: ArrayList<Todo> = ArrayList<Todo>()
        homeRespoitory.getTodosList()
            .subscribeOn(Schedulers.io())
            .map {
                todosList=it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                todosLiveData.value = todosList
            }
    }

    fun addTodo(todo: Todo) = homeRespoitory.addToFavs(todo)
    fun deleteTodo(id: Int) = homeRespoitory.removeFromFavs(id)
    fun getAllFavorites(){
        favLiveData= homeRespoitory.getAllFavs()
    }


}