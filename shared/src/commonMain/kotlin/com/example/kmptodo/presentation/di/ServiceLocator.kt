package com.example.kmptodo.presentation.di

import com.example.kmptodo.data.InMemoryTodoRepository
import com.example.kmptodo.domain.repository.TodoRepository
import com.example.kmptodo.domain.usecase.AddTodo
import com.example.kmptodo.domain.usecase.RemoveTodo
import com.example.kmptodo.domain.usecase.ToggleTodo
import com.example.kmptodo.presentation.vm.TodoViewModel

object ServiceLocator {
    private val repo: TodoRepository by lazy { InMemoryTodoRepository() }

    private val add by lazy { AddTodo(repo) }
    private val toggle by lazy { ToggleTodo(repo) }
    private val remove by lazy { RemoveTodo(repo) }

    fun provideTodoViewModel(): TodoViewModel =
        TodoViewModel(repo, add, toggle, remove)
}
