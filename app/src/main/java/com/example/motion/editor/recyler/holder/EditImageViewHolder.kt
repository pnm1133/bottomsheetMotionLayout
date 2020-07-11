package com.example.motion.editor.recyler.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motion.editor.model.ImageModel
import com.example.motion.editor.view.EditorListener
import com.example.motion.editor.view.chilld.image.ImageViewGroup
import com.example.motion.utils.logE

class EditImageViewHolder (private val imageView : ImageViewGroup, val callback : EditorListener) : RecyclerView.ViewHolder(imageView) {

    fun bind(imageModel : ImageModel){
        imageView.bind(imageModel)
    }

    companion object {
        fun create(parent : ViewGroup,callback : EditorListener) : EditImageViewHolder{
            return EditImageViewHolder(ImageViewGroup(parent.context),callback)
        }
    }
}