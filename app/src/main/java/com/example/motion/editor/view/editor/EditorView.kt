package com.example.motion.editor.view.editor

import com.example.motion.editor.EditorModel
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.model.ImageModel
import com.example.motion.editor.view.chilld.EditorViewBase

interface EditorView {

    val models: MutableList<EditorModel>

    suspend fun setContentChar(positon: Int, char: CharModel): Boolean

    suspend fun setContentImage(positon: Int, imageModel: ImageModel): Boolean

    suspend fun swapPosition(firstModel: EditorModel, secondModel: EditorModel): Boolean

    suspend fun getModels(): List<EditorModel>

    suspend fun newImageView(vararg imageModel: ImageModel)

    suspend fun newCharView(vararg charModel: CharModel)

    suspend fun currentFocusView(): EditorViewBase?

    fun showBottomHalfExpand()

    fun applyChange()

}