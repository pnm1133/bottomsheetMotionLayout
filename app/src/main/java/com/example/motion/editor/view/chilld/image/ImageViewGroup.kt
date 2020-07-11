package com.example.motion.editor.view.chilld.image

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.example.motion.R
import com.example.motion.editor.model.ImageModel
import com.example.motion.editor.view.chilld.image.ImageView
import com.example.motion.utils.JobUtil
import kotlinx.coroutines.Dispatchers
import java.util.*

class ImageViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, def: Int = 0) : ConstraintLayout(context, attrs, def), ImageView {

    override val isFocusing: MutableLiveData<Boolean> by lazy {  MutableLiveData(false) }
    private var imageModel : ImageModel? = null
    override var id: UUID? = imageModel?.id

    init {
        View.inflate(context, R.layout.image_view_group,this)
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    fun bind(model: ImageModel?){
        JobUtil.doJob(Dispatchers.Main){
            imageModel = model
        }
    }

    override suspend fun onFocusingState(turnOn: Boolean): Boolean {
        return false
    }

    override  fun setImageResource(url: String?) {

    }

    override  fun setImageResource(bitmap: Bitmap?) {

    }

    override var position: Int? = null
}