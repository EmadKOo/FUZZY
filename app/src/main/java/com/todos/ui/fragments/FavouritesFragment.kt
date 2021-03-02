package com.todos.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todos.R
import com.todos.adapters.HomeAdapter
import com.todos.pojo.Todo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesFragment : BaseFragment() {

    @Inject lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getAllFavorites()
        homeViewModel.getFavLiveDate().observe(requireActivity(), Observer {
            Log.d("TAG", "onViewCreated: " + it[0].title)
            var todoList = ArrayList<Todo>()
            todoList.addAll(it)
            homeAdapter.setList(todoList)
        })

        initRecyclerView()

    }

    fun initRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        favouritesRecyclerView.layoutManager = layoutManager
        favouritesRecyclerView.adapter = homeAdapter
        handleSwipe()
    }

    fun handleSwipe(){
        val itemTouchHelperCallback =
                object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        homeViewModel.deleteTodo(homeAdapter.getTodo(viewHolder.adapterPosition).id)
                        homeAdapter.notifyDataSetChanged()
                    }

                }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(favouritesRecyclerView)
    }
}