package com.example.motion.utils
import com.google.android.material.appbar.AppBarLayout

fun AppBarLayout?.isElevation(turnOn : Boolean){
    if (turnOn && this?.elevation != 15F){
        this?.elevation = 15F
    }else if(!turnOn && this?.elevation != 0F) {
        this?.elevation = 0F
    }
}