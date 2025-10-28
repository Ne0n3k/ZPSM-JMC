package com.example.kmptodo.presentation.state

import com.example.kmptodo.domain.entity.TodoItem

data class TodoState(
    val items: List<TodoItem> = emptyList(),
    val input: String = ""
)
