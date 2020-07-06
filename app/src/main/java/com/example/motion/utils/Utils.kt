package com.example.motion.utils

import android.util.Log
import android.view.View
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


object JobUtil {
    inline fun <reified T : CoroutineDispatcher> doJob(
        dispatcher: T,
        noinline doF: suspend CoroutineScope.() -> Unit) {
        val scope = CoroutineScope(dispatcher)
        val job = scope.launch {
            doF.invoke(this)
        }
        job.invokeOnCompletion {
            if (!job.isCancelled) {
                job.cancel()
            }
            scope.cancel()
        }
    }
}


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