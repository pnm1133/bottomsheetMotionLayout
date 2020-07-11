package com.example.motion.motion

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import com.example.motion.R
import com.example.motion.utils.JobUtil
import com.example.motion.utils.isElevation
import com.example.motion.utils.logE
import com.example.motion.utils.ofType
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.bottom_sheet_drawer.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class BottomSheetMotionView @JvmOverloads constructor(context: Context, attrs: AttributeSet, dif: Int = 0) : BottomSheetBaseView(context, attrs, dif) {

    private var marginTop : Float = 0F
    var isShowExpandIcon : Boolean = true
    set(value) {
        _imvExpandId.isVisible = value
        field = value
    }
    var isFullScreenStyle : Boolean = true
    set(value) {
        marginTop = if(value){
            0F
        }else {
            val values = resources.getDimension(R.dimen.margin_top_bottom_sheet)
            values
        }
        val top = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,marginTop,resources.displayMetrics).toInt()
        val margin = LayoutParams(MarginLayoutParams.MATCH_PARENT,top)
        _spacerId?.layoutParams = margin
        field = value
    }

    var radiusBottomSheet : Float = 2F
    set(value) {
        bottomSheetContentId.topCorner = value
        field = value
    }

    init {
        setBackgroundColor(ActivityCompat.getColor(context, android.R.color.transparent))
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.BottomSheetMotionView)
        radiusBottomSheet = attributes.getDimension(R.styleable.BottomSheetMotionView_radius, 0F)
        isFullScreenStyle = attributes.getBoolean(R.styleable.BottomSheetMotionView_isFullScreen, true)
        isShowExpandIcon = attributes.getBoolean(R.styleable.BottomSheetMotionView_showExpandIcon, true)
        attributes.recycle()
    }

    override fun onFinishInflate() {
        radiusBottomSheet = resources.getDimension(R.dimen.normal_size)
        super.onFinishInflate()
    }

    fun haftExpand(){
        "haftExpand".logE()
        JobUtil.doJob(Dispatchers.Main){
            delay(100)
            setTransition(R.id._transitionBottomGone)
            transitionToEnd()
        }
    }

    fun expand(){
        "haftExpand".logE()
        JobUtil.doJob(Dispatchers.Main){
            delay(100)
            setTransition(R.id._transitionBottomHaftExpand)
            transitionToEnd()
        }
    }

    fun inflateFr(fr : Fragment){
        kotlin.runCatching {
            (context as AppCompatActivity?)?.supportFragmentManager?.beginTransaction()?.add(R.id._frContentId,fr)?.commit()
        }
    }
}

