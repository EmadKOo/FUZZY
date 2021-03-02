package com.todos.network

import com.todos.pojo.Todo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import javax.inject.Inject


interface TodosApiService {

    @GET("posts")
    fun getTodos(): Observable<ArrayList<Todo>>
}