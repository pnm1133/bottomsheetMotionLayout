package com.example.motion.editor.view.motion

interface FocusView {

    suspend fun onFocusState(turnOn : Boolean) : Boolean
}