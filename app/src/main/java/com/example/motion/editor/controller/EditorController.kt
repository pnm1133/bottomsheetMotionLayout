package com.example.motion.editor.controller

import com.example.motion.editor.EditorModel
import com.example.motion.editor.RichEditor
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.utils.ofType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditorController(private val modes : MutableList<EditorModel>) : RichEditor {

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

    override suspend fun addNewModel(newModel: EditorModel): Boolean = withContext(Dispatchers.Default){
        if(modes.isNotEmpty() && modes.size >= 1){
            modes.add(newModel)
            val last = modes.last()
            val semiLast = modes[modes.size - 2]
            if(ofType<CharModel>(last) && ofType<CharModel>(semiLast)){
                val modelAppended = mergerCharModel(semiLast as CharModel ,last as CharModel)
                modelAppended?.let {
                    modes.add(it)
                    modes.remove(last)
                    modes.remove(semiLast)
                    true
                } ?: false
            }else {
                modes.find { it.id == newModel.id }?.let { modes.remove(it) }
                modes.add(newModel)
                true
            }
        }else {
            modes.find { it.id == newModel.id }?.let { modes.remove(it) }
            modes.add(newModel)
            true
        }
    }

    override suspend fun addNewModel(newModels: List<EditorModel>): Boolean = withContext(Dispatchers.Default){
        newModels.forEach { newModel->
            if(modes.isNotEmpty() && modes.size >= 1){
                modes.add(newModel)
                val last = modes.last()
                val semiLast = modes[modes.size - 2]
                if(ofType<CharModel>(last) && ofType<CharModel>(semiLast)){
                    val modelAppended = mergerCharModel(semiLast as CharModel ,last as CharModel)
                    modelAppended?.let {
                        modes.add(it)
                        modes.remove(last)
                        modes.remove(semiLast)
                    }
                }else {
                    modes.find { it.id == newModel.id }?.let { modes.remove(it) }
                    modes.add(newModel)
                }
            }else {
                modes.find { it.id == newModel.id }?.let { modes.remove(it) }
                modes.add(newModel)
            }
        }
        modes.distinct()
         true
    }


    private suspend fun mergerCharModel(firstChar : CharModel , secondChar : CharModel) : CharModel? = firstChar.appendCharModel(secondChar)
}