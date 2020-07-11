package com.example.motion.editor.recyler.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.view.EditorListener
import com.example.motion.editor.view.chilld.charview.CharViewGroup
import com.example.motion.utils.logE

class EditorCharViewHolder(private val charView : CharViewGroup, val callback: EditorListener) : RecyclerView.ViewHolder(charView){

    fun bind(charModel : CharModel){
        "EditImageViewHolder bind".logE()
        charView.bind(charModel)
        charView.editorCallback = callback
    }

    companion object {
        fun create(parent : ViewGroup,callback : EditorListener) : EditorCharViewHolder{
            return EditorCharViewHolder(CharViewGroup(parent.context),callback)
        }
    }
}