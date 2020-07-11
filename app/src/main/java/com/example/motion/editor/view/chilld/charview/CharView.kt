package com.example.motion.editor.view.chilld.charview

import com.example.motion.editor.model.CharModel
import com.example.motion.editor.model.LocationModel
import com.example.motion.editor.model.UserModel
import com.example.motion.editor.view.chilld.EditorViewBase

interface CharView : EditorViewBase {

    var charModel : CharModel?

    fun setContentChar(charSequence: CharSequence?)

    fun setWithUser(vararg userModel: UserModel?)

    fun setLocation(vararg userModel: LocationModel?)

    fun bind(charModel: CharModel?)

}