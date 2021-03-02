package com.todos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.todos.R
import com.todos.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_home_Fragmnet) as NavHostFragment
        navController = navHostFragment.findNavController()

//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        homeViewModel.getTodos()
//
//        homeViewModel.getTodosLiveData().observe(this, Observer {
//            Log.d("TAG", "onCreate: MYLIST " + it[0].title)
//        })
    }
}