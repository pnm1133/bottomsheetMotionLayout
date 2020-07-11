package com.example.motion.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.motion.R
import com.example.motion.utils.JobUtil
import kotlinx.android.synthetic.main.splash_flight_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class SplashFlightView  @JvmOverloads constructor(context: Context, attrs: AttributeSet, dif: Int = 0) : ConstraintLayout(context, attrs, dif){
    init {
        layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        View.inflate(context, R.layout.splash_flight_view, this@SplashFlightView)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        start()
    }

    fun start(){
        setOnClickListener {
            JobUtil.doJob(Dispatchers.Main){
                delay(10)
                _motion?.transitionToEnd()
                delay(800)
                _splashId?.start()
            }
        }

    }
}