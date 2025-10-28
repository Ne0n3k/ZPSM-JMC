package com.example.kmptodo.domain.usecase

import com.example.kmptodo.domain.repository.TodoRepository

class AddTodo(private val repo: TodoRepository) {
    operator fun invoke(text: String) = repo.add(text)
}
