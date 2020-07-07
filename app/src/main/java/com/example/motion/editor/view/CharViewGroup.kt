package com.example.motion.editor.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.motion.editor.model.CharModel

class CharViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, def: Int = 0) : LinearLayout(context, attrs, def) , CharView{

    override var isFocusing: Boolean = false

    override suspend fun onFocusingState(turnOn: Boolean): Boolean {
        TODO("Not yet implemented")
    }

}