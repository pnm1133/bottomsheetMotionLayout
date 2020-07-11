package com.example.motion.editor.view

import com.example.motion.editor.view.chilld.EditorViewBase

interface EditorListener {

    fun onClickItem(viewBase: EditorViewBase,position : Int)

    //fun onModelCharChange(editable : Editable?,id : UUID?)
}