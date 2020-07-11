package com.example.motion.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.example.motion.R
import com.example.motion.utils.JobUtil
import kotlinx.android.synthetic.main.motion_flight_view.view.*
import kotlinx.android.synthetic.main.splash_flight_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class MotionFlightView @JvmOverloads constructor(context: Context, attrs: AttributeSet, dif: Int = 0) : FrameLayout(context, attrs, dif){

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        View.inflate(context, R.layout.motion_flight_view, this@MotionFlightView)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        start()
    }

    fun start(){
        JobUtil.doJob(Dispatchers.Main){
            delay(10)
            _motionFlightDetail?.setTransition(R.id._transitionToEndFlight)
            _motionFlightDetail?.transitionToEnd()
        }
    }
}