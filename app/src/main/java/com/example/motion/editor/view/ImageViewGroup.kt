package com.example.motion.editor.view

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.motion.editor.model.ImageModel

class ImageViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, def: Int = 0) : FrameLayout(context, attrs, def), ImageView{

    override var isFocusing: Boolean = false

    override suspend fun onFocusingState(turnOn: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override  fun setImageResource(url: String?) {
        TODO("Not yet implemented")
    }

    override  fun setImageResource(bitmap: Bitmap?) {
        TODO("Not yet implemented")
    }
}