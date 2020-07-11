package com.example.motion.motion

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.motion.R

class IndicatorMotionView @JvmOverloads constructor(context: Context, attrs: AttributeSet, dif: Int = 0) : FrameLayout(context, attrs, dif){
    init {
        layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        View.inflate(context, R.layout.indicator_motion_view, this@IndicatorMotionView)
    }
}