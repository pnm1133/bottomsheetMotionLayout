package com.example.motion.motion

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.motion.R
import com.example.motion.utils.isElevation
import com.example.motion.utils.logE
import com.example.motion.utils.ofType
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.bottom_sheet_drawer.view.*

abstract class BottomSheetBaseView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet,
    dif: Int = 0
) : CoordinatorLayout(context, attr, dif) {

    enum class State {
        COLLAPSE, HALF, EXPAND
    }

    private var appbar: AppBarLayout? = null

    private var coordinatorLayoutParent: CoordinatorLayout? = null

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        View.inflate(context, R.layout.bottom_sheet_drawer, this)

    }


    override fun onFinishInflate() {
        val child0 = getChildAt(0)
        this.removeViewAt(0)
        this.addView(child0,childCount)
        super.onFinishInflate()
        initView()
    }

    private fun initLayout() {
        _parentMotionId.setTransitionListener(object : MotionLayout.TransitionListener {

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, process: Float) {

            }

            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {

            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, process: Float) {
                if (isSetHaftToExpand(startId, endId)) {
                    if (process >= 0.1F) {
                        appbar.isElevation(false)
                    } else {
                        appbar.isElevation(true)
                    }
                    _ViewId?.alpha = process
                    if (_ViewId?.alpha == 0F) {
                        _ViewId.isVisible = false
                    } else {
                        if (!_ViewId.isVisible) _ViewId.isVisible = true
                    }
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, p1: Int) {

            }
        })

        initAction()
    }

    private fun initAction() {
        _imvExpandId.setOnClickListener {
            _parentMotionId?.transitionToEnd()
        }
        /*_ViewId?.setOnClickListener {
            "view Click ".logE()
            _parentMotionId?.transitionToStart()
        }*/
    }

    private fun initView() {
        // find parent find appbar
        viewTreeObserver.addOnGlobalLayoutListener {
            /*if (coordinatorLayoutParent != null) return@addOnGlobalLayoutListener
            coordinatorLayoutParent =
                parent.ofType(CoordinatorLayout::class.java) as? CoordinatorLayout
            require(coordinatorLayoutParent != null) {
                "parent layout must coordinator layout"
            }*/
            if (appbar != null) return@addOnGlobalLayoutListener
            appbar = findAppbarOfParent(this)
            "appbar $appbar".logE()
            initLayout()
        }
    }

    private fun findAppbarOfParent(parent: CoordinatorLayout): AppBarLayout? {
        var appbar: AppBarLayout? = null
        parent.run {
            for (i in 0 until childCount) {
                appbar = getChildAt(i).ofType(AppBarLayout::class.java) as? AppBarLayout
                if (appbar != null) break
            }
        }
        return appbar
    }

    override fun onDetachedFromWindow() {
        clearFindViewByIdCache()
        super.onDetachedFromWindow()
    }

    private fun isSetHaftToExpand(startId: Int, endId: Int) =
        startId == R.id._setHaftExpand && endId == R.id._setExpand

    private fun isSetCollapseToHaft(startId: Int, endId: Int) =
        startId == R.id._setCollapse && endId == R.id._setHaftExpand

}