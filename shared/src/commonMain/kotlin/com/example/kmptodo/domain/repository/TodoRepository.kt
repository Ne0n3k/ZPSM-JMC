package com.example.kmptodo.domain.repository

import com.example.kmptodo.domain.entity.TodoItem
import kotlinx.coroutines.flow.StateFlow

interface TodoRepository {
    val items: StateFlow<List<TodoItem>>
    fun add(text: String)
    fun toggle(id: Long)
    fun remove(id: Long)
}
