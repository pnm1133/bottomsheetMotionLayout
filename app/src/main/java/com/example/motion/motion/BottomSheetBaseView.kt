package com.example.motion.motion

import android.app.Dialog
import android.content.Context
import android.util.AttributeSet
import android.util.LayoutDirection
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.motion.R
import com.example.motion.utils.JobUtil
import com.example.motion.utils.isElevation
import com.example.motion.utils.logE
import com.example.motion.utils.ofType
import com.example.motion.view.BottomSheetNestestScroll
import com.example.motion.view.ScrollViewListener
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.bottom_sheet_drawer.view.*
import kotlinx.coroutines.*
import kotlin.coroutines.resume

abstract class BottomSheetBaseView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet,
    dif: Int = 0
) : MotionLayout(context, attr, dif) {


    enum class State {
        COLLAPSE,
        HALF,
        EXPAND
    }

    private var appbar: AppBarLayout? = null
    private var scrollView : BottomSheetNestestScroll? = null

    init {
        layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        View.inflate(context, R.layout.bottom_sheet_drawer, this@BottomSheetBaseView)
        id = R.id._motionBottomSheet
    }

    var isStop = true
    override fun onFinishInflate() {
        CoroutineScope(Dispatchers.Main).launch {
            loadMotionScene()
            super.onFinishInflate()
            initLayout()
            appbar = findAppbarOfParent(this@BottomSheetBaseView)
            scrollView = findScrollViewOfParent(this@BottomSheetBaseView)
            reorderView()
        }.invokeOnCompletion {
            scrollView?.setScrollViewListener(object : ScrollViewListener {
                override fun onStopScroll() {
                    JobUtil.doJob(Dispatchers.Main){
                        delay(200)
                        val isStops =  !isStop
                        if(isStops){
                            "100F".logE()
                        }else {
                            "-100F".logE()
                        }
                        isStop = isStops
                    }
                }

                override fun onStartScroll() {
                    JobUtil.doJob(Dispatchers.Main){
                        delay(200)

                    }
                }
            })
        }
    }

    private suspend fun loadMotionScene() {
        delay(100)
        loadLayoutDescription(R.xml.bottom_sheet_scene)
        setTransition(R.id._transitionBottomGone)
        setDebugMode(3)
    }

    private fun initLayout() {
        setTransitionListener(object : TransitionListener {

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
                }else if(isSetSwipe(startId,endId)){
                    "process $process".logE()
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, p1: Int) {

            }
        })

        initAction()
    }

    private fun initAction() {
        _imvExpandId.setOnClickListener {
            "_imvExpandId Click".logE()
            transitionToEnd()
        }
        _ViewId.setOnClickListener {
            transitionToStart()
        }
    }

    private suspend fun findAppbarOfParent(parent: MotionLayout): AppBarLayout?= suspendCancellableCoroutine<AppBarLayout> {
        viewTreeObserver.addOnGlobalLayoutListener {
            parent.run {
                for (i in 0 until childCount) {
                    val appbar = getChildAt(i).ofType(AppBarLayout::class.java) as? AppBarLayout?
                    if(appbar != null && !it.isCompleted){
                        it.resume(appbar)
                    }
                }
            }
        }
    }

    private suspend fun findScrollViewOfParent(parent: MotionLayout): BottomSheetNestestScroll?= suspendCancellableCoroutine {
        viewTreeObserver.addOnGlobalLayoutListener {
            parent.run {
                for (i in 0 until childCount) {
                    val appbar = getChildAt(i).ofType(BottomSheetNestestScroll::class.java) as? BottomSheetNestestScroll?
                    if(appbar != null && !it.isCompleted){
                        it.resume(appbar)
                    }
                }
            }
        }
    }

    private fun reorderView()  {
        _imvExpandId.translationZ = 1F
        _ViewId.translationZ = 1F
        bottomSheetContentId.translationZ = 1F
    }

    override fun onDetachedFromWindow() {
        clearFindViewByIdCache()
        super.onDetachedFromWindow()
    }

    private fun isSetHaftToExpand(startId: Int, endId: Int) =
        startId == R.id._setHaftExpand && endId == R.id._setExpand

    private fun isSetCollapseToHaft(startId: Int, endId: Int) =
        startId == R.id._setCollapse && endId == R.id._setHaftExpand


    private fun isSetSwipe(startId: Int, endId: Int) =
        startId == R.id._setCollapse && endId == R.id._setCollapse

}
