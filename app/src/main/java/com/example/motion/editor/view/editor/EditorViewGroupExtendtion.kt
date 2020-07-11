package com.example.motion.editor.view.editor

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motion.editor.recyler.adapter.EditorViewBaseAdapter
import kotlinx.android.synthetic.main.editor_view_group.view.*

internal fun EditorViewGroup.initLayout(){
    editorViewBaseAdapter = EditorViewBaseAdapter(models,this)
    _listEditViewRecId?.let { rcl ->
        rcl.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rcl.adapter = editorViewBaseAdapter
    }
}


internal fun EditorViewGroup.require(){

}