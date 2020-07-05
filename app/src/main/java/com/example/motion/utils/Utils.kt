package com.example.motion.utils

import android.util.Log

fun String.logE(){
    Log.e("===",this)
}

inline fun <reified T> T.ofType(c : Class<*>) : T? {
    return if(c.isInstance(this)){
        this
    }else {
        null
    }
}