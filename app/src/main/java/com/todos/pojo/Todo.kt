package com.todos.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
 class Todo(val userId: Int=-1, @PrimaryKey val id: Int= -1, val title: String="", val body: String=""){}