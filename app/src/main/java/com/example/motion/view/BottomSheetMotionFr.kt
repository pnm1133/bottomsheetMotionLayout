package com.example.motion.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.motion.R

class BottomSheetMotionFr : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.inflate(R.layout.bottom_sheet_content,container,false)
        return inflater.inflate(R.layout.bottom_sheet_content,container,false)
    }
}