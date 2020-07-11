package com.example.motion.editor.view.chilld.image

import android.graphics.Bitmap
import com.example.motion.editor.view.chilld.EditorViewBase

interface ImageView : EditorViewBase {

    fun setImageResource(url: String?)

    fun setImageResource(bitmap: Bitmap?)
}