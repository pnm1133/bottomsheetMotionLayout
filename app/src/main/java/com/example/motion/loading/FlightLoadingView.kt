package com.example.motion.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.motion.R
import com.example.motion.utils.JobUtil
import com.example.motion.utils.logE
import kotlinx.android.synthetic.main.flight_loading.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

class FlightLoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet, dif: Int = 0) : MotionLayout(context, attrs, dif){

    init {
        layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        View.inflate(context, R.layout.flight_loading, this@FlightLoadingView)
    }

    var next = true

    override fun onFinishInflate() {
        JobUtil.doJob(Dispatchers.Main){
            delay(100)
            loadLayoutDescription(R.xml.flight_loading_scence)
            setTransition(R.id._transition_Flight_Start)
            //setDebugMode(3)
        }
        super.onFinishInflate()

        setTransitionListener(object  : TransitionListener {

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, process: Float) {

            }

            override fun onTransitionCompleted(motion: MotionLayout?, transition : Int) {
                if(transition == R.id._setCenter_Flight){
                    "end Start ".logE()
                    JobUtil.doJob(Dispatchers.Main){
                        withTimeout(3000){
                            if(next){
                                setTransition(R.id._transition_Flight_Start_Around)
                                transitionToEnd()
                            }
                        }
                    }
                }
            }
        })

        _btnStopId?.setOnClickListener {
            next = false
        }
    }

}