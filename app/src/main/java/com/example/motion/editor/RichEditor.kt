package com.example.motion.editor


interface EditorModel {
    var position : Int
}

interface RichEditor {

    fun cacheModel() : List<EditorModel>

    fun parseExperience() : List<EditorModel>

    fun postExperience() : Boolean

    fun editExperience() : Boolean

    fun startWriteExperience() : Boolean
}