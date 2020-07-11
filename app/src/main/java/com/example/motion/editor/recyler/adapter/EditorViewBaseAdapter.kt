package com.example.motion.editor.recyler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motion.editor.EditorModel
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.model.ImageModel
import com.example.motion.editor.recyler.holder.EditImageViewHolder
import com.example.motion.editor.recyler.holder.EditorCharViewHolder
import com.example.motion.editor.utils.ofType
import com.example.motion.editor.view.EditorListener

class EditorViewBaseAdapter(val models: MutableList<EditorModel>,val callback : EditorListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addModel(model: EditorModel) {

    }

    fun addLastModel() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                EditorCharViewHolder.create(parent,callback)
            }
            2 -> {
                EditImageViewHolder.create(parent,callback)
            }
            else -> {
                super.createViewHolder(parent, viewType)
            }
        }
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EditorCharViewHolder -> {
                holder.bind(models[position] as CharModel)
            }
            is EditImageViewHolder -> {
                holder.bind(models[position] as ImageModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        kotlin.runCatching {
            val model = models[position]
            return when {
                ofType<CharModel>(model) -> 1
                ofType<ImageModel>(model) -> 2
                else -> super.getItemViewType(position)
            }
        }.getOrNull() ?: return super.getItemViewType(position)
    }
}