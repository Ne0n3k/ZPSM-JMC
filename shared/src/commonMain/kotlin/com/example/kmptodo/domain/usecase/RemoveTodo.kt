package com.example.kmptodo.domain.usecase
import com.example.kmptodo.domain.repository.TodoRepository

class RemoveTodo(private val repo: TodoRepository) {
    operator fun invoke(id: Long) = repo.remove(id)
}
