package com.example.kmptodo.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.kmptodo.ui.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KmpTodo") { App() }
}
