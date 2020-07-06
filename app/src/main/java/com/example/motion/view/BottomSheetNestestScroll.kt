package com.example.motion.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import com.example.motion.utils.logE

interface ScrollViewListener {
    /*fun onScrollChanged(scrollView: BottomSheetNestestScroll?,
                        x: Int, y: Int, oldx: Int, oldy: Int)*/
    fun onStopScroll()

    fun onStartScroll()

}
class BottomSheetNestestScroll  @JvmOverloads constructor(context: Context, attr: AttributeSet, dif: Int = 0) : ScrollView(context, attr, dif){

    override fun onFinishInflate() {
        super.onFinishInflate()
        computeScroll()
    }

    private var scrollViewListener: ScrollViewListener? = null

    fun setScrollViewListener(scrollViewListener: ScrollViewListener?) {
        this.scrollViewListener = scrollViewListener
    }

    override fun onStopNestedScroll(target: View) {
        "onStopNestedScroll".logE()
        super.onStopNestedScroll(target)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        val view = getChildAt(childCount - 1) as View
        val diff: Int = view.bottom - (height + scrollY)

        // if diff is zero, then the bottom has been reached

        // if diff is zero, then the bottom has been reached

        if (diff == 0) {
            // do stuff
        }
        /*if (scrollViewListener != null) {
            scrollViewListener?.onScrollChanged(this, l, t, oldl, oldt)
        }*/
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_SCROLL, MotionEvent.ACTION_MOVE ->{
                scrollViewListener?.onStartScroll()
                "ACTION_SCROLL".logE()
            }
            MotionEvent.ACTION_DOWN -> "ACTION_DOWN".logE()
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                scrollViewListener?.onStopScroll()
            }
        }
        return super.onTouchEvent(event)
    }

}