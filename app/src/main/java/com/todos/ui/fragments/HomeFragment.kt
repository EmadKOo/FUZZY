package com.todos.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todos.R
import com.todos.adapters.HomeAdapter
import com.todos.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {


    @Inject lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getTodos()
        initRecyclerView()
        favButton.setOnClickListener{
            goToFavourites()
        }

        homeViewModel.getTodosLiveData().observe(requireActivity(), Observer {
            homeAdapter.setList(it)
        })
    }

    fun initRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.adapter = homeAdapter
        handleSwipe()
    }

    fun handleSwipe(){
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    homeViewModel.addTodo(homeAdapter.getTodo(viewHolder.adapterPosition))
                    homeAdapter.notifyDataSetChanged()
                }

            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(homeRecyclerView)
    }

    fun goToFavourites(){
        val action = HomeFragmentDirections.actionHomeFragmentToFavouritesFragment()
        findNavController().navigate(action)
    }
}