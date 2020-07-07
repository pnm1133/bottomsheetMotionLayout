package com.example.motion.editor.controller

import com.example.motion.editor.EditorModel
import com.example.motion.editor.RichEditor

class EditorController : RichEditor {

    override fun cacheModel(): List<EditorModel> {
        return emptyList()
    }

    override fun parseExperience(): List<EditorModel> {

        return emptyList()
    }

    override fun postExperience(): Boolean {
        return true
    }

    override fun editExperience(): Boolean {
        return true
    }

    override fun startWriteExperience(): Boolean {
        return true
    }
}