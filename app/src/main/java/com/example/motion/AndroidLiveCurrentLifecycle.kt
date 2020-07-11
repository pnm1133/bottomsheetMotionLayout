package com.example.motion

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

object AndroidLiveCurrentLifecycle {

    var lifecycleOwner : WeakReference<LifecycleOwner>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        release()
    }

    fun registerActionHandler(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = WeakReference(lifecycleOwner)
    }

    private fun release(){
        this.lifecycleOwner?.clear()
    }
}