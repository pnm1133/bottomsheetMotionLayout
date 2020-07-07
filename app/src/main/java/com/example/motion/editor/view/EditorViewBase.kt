package com.example.motion.editor.view

interface EditorViewBase {

    var isFocusing : Boolean

    suspend fun onFocusingState(turnOn : Boolean) : Boolean
}