package com.example.motion.editor.view.chilld

import androidx.lifecycle.MutableLiveData
import java.util.*

interface EditorViewBase {

    var id : UUID?

    var position : Int?

    val isFocusing : MutableLiveData<Boolean>

    suspend fun onFocusingState(turnOn : Boolean) : Boolean
}