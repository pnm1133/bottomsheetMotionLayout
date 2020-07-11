package com.example.motion.editor

import java.util.*


interface EditorModel {

    val id : UUID

    var isFocusing : Boolean
}

interface RichEditor {

    fun cacheModel(): List<EditorModel>

    fun parseExperience(): List<EditorModel>

    fun postExperience(): Boolean

    fun editExperience(): Boolean

    fun startWriteExperience(): Boolean

    suspend fun addNewModel(newModel: EditorModel) : Boolean

    suspend fun addNewModel(newModels: List<EditorModel>) : Boolean
}