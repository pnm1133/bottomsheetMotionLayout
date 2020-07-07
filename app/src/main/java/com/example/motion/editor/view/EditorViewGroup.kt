package com.example.motion.editor.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Space
import com.example.motion.editor.EditorModel
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.model.ImageModel
import com.example.motion.editor.model.ImageModelBase
import com.example.motion.utils.JobUtil
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.util.*

class EditorViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, def: Int = 0) : LinearLayout(context, attrs, def), EditorView {

    override val models: MutableList<EditorModel> = mutableListOf()

    override suspend fun setContentChar(positon: Int, char: CharModel): Boolean {
        return runCatching {
            val child = getChildAt(positon)
            if (child is CharViewGroup?) {

            }
        }.isSuccess
    }

    override suspend fun setContentImage(positon: Int, imageModel: ImageModel): Boolean {
        return runCatching {
            val child = getChildAt(positon)
            if (child is ImageViewGroup?) {
                imageModel.path?.let { child?.setImageResource(it) } ?: imageModel.bitmap?.let { child?.setImageResource(it) }
            }
        }.isSuccess
    }

    override suspend fun swapPosition(firstModel: EditorModel, secondModel: EditorModel): Boolean {
        return kotlin.runCatching {
            val child1 = getChildAt(firstModel.position)
            val child2 = getChildAt(secondModel.position)
            removeViewAt(firstModel.position)
            removeViewAt(secondModel.position)
            addView(child1)
            addView(child2)
        }.isSuccess
    }

    override suspend fun getModels(): List<EditorModel> = models

    override suspend fun newImageView(imageModel: ImageModel) {
        kotlin.runCatching {
            val imageView = ImageViewGroup(context)
            models.add(imageModel)
            addView(imageView)
            setContentImage(childCount - 1,imageModel)
        }
    }

    override suspend fun newCharView(charModel: CharModel) {
        val charGroup = CharViewGroup(context)
        models.add(charModel)
        addView(charGroup)
    }

    override suspend fun currentFocusView(): EditorView? {
        TODO("Not yet implemented")
    }
}