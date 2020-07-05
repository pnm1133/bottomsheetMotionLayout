package com.example.motion.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.Dimension
import com.example.motion.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

interface MaterialDragCallback{

}


class MaterialDynamicCorner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    @Dimension
    var topCorner: Float = 0F
        set(value) {
            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setTopLeftCorner(CornerFamily.ROUNDED, value)
                .setTopRightCorner(CornerFamily.ROUNDED, value)
                .build()
            field = value
        }

    private val shapeModelExpand: ShapeAppearanceModel by lazy {
        shapeAppearanceModel.toBuilder()
            .setTopLeftCornerSize(0F)
            .setTopRightCornerSize(0F)
            .build()
    }

    private val shapeModelCollapsed: ShapeAppearanceModel by lazy {
        shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, topCorner)
            .setTopRightCorner(CornerFamily.ROUNDED,topCorner)
            .build()
    }

    fun expanded() {
        if (shapeAppearanceModel == shapeModelExpand) return
        Log.i("===", "expanded $topCorner")
        shapeAppearanceModel = shapeModelExpand
    }

    fun collapsed() {
        if (shapeAppearanceModel == shapeModelCollapsed) return
        Log.i("===", "collapsed ")
        shapeAppearanceModel = shapeModelCollapsed
    }

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MaterialDynamicCorner)
        topCorner = attributes.getDimension(R.styleable.MaterialDynamicCorner_topRadius, 0F)
        attributes.recycle()
    }
}