package com.example.motion.editor.view

import com.example.motion.editor.EditorModel
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.model.ImageModel

interface EditorView {

    val models : MutableList<EditorModel>

    suspend fun setContentChar(positon: Int ,char : CharModel) : Boolean

    suspend fun setContentImage(positon: Int,imageModel: ImageModel) : Boolean

    suspend fun swapPosition(firstModel : EditorModel , secondModel : EditorModel) : Boolean

    suspend fun getModels() : List<EditorModel>

    suspend fun newImageView(imageModel: ImageModel)

    suspend fun newCharView(charModel: CharModel)

    suspend fun currentFocusView() : EditorView?

}