package com.example.motion.editor.model

import android.graphics.Bitmap
import com.example.motion.editor.EditorModel
import java.util.*

interface ImageModel : EditorModel {

    var path : String?

    var bitmap : Bitmap?

}

data class ImageModelBase(override var isFocusing: Boolean= false, override var id: UUID = UUID.randomUUID(), override var path: String? = null, override var bitmap: Bitmap? = null) : ImageModel