package com.todos.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.todos.db.TodoDAO
import com.todos.db.TodosDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides @Singleton
    fun provideRoomDB( app: Application):TodosDB {
            return Room.databaseBuilder(app, TodosDB::class.java, "todosDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }


    @Provides @Singleton
    fun providesDao(todoDB: TodosDB): TodoDAO{
        return todoDB.todoDao()
    }

}