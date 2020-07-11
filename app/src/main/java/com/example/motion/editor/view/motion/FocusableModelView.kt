package com.example.motion.editor.view.motion

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.motion.R
import com.example.motion.utils.JobUtil
import com.example.motion.utils.logE
import kotlinx.android.synthetic.main.focusable_motion_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class FocusableModelView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet,
    dif: Int = 0) : MotionLayout(context, attr, dif),FocusView {

    init {
        View.inflate(context,R.layout.focusable_motion_view,this)
    }

    override fun onFinishInflate() {
        inflateMotionSence()
        super.onFinishInflate()

    }

    private fun inflateMotionSence() {
        JobUtil.doJob(Dispatchers.Main){
            delay(100)
            loadLayoutDescription(R.xml.focusable_motion_view_scene)
            setTransition(R.id._transition_Focus_View)
            val child = getChildAt(0)
            removeViewAt(0)
            _content?.addView(child)
        }
    }

    override suspend fun onFocusState(turnOn: Boolean): Boolean = false

    fun focusedBg(){
        "focusedBg".logE()
        transitionToEnd()
    }

    fun unfocusedBg(){
        "unfocusedBg".logE()
        transitionToStart()
    }

}