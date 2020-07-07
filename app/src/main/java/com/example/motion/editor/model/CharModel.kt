package com.example.motion.editor.model

import com.example.motion.editor.EditorModel
import java.util.*

interface CharModel : EditorModel {

    var position: Int

    val id: UUID

    var char : CharSequence

    var userModels : List<UserModel>?

    var locationModels : List<LocationModel>?

    suspend fun appendWith(userModel: UserModel) : Boolean

    suspend fun appendLocation(locationModel: LocationModel) : Boolean

}