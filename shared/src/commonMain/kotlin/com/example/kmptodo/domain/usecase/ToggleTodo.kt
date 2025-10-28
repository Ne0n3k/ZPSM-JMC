package com.example.kmptodo.domain.usecase
import com.example.kmptodo.domain.repository.TodoRepository

class ToggleTodo(private val repo: TodoRepository) {
    operator fun invoke(id: Long) = repo.toggle(id)
}
