package com.example.motion.motion

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.motion.utils.logE

class CoordinatorBottomSheetView @JvmOverloads constructor(context: Context, attr: AttributeSet, dif: Int = 0) : CoordinatorLayout(context, attr, dif) {


    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        "velocityX $velocityX".logE()
        return super.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        "velocityX $dxConsumed".logE()
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }
}