package com.example.motion.editor.view

import android.graphics.Bitmap
import com.example.motion.editor.model.ImageModel

interface ImageView : EditorViewBase {

    fun setImageResource(url: String?)

    fun setImageResource(bitmap: Bitmap?)
}