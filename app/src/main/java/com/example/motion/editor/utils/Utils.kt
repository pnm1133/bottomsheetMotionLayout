package com.example.motion.editor.utils

inline fun <reified T> ofType(a : Any?) : Boolean {
    return runCatching {
        val classT = T::class.java
        classT.isInstance(a)
    }.getOrNull() ?: false
}

inline fun <reified T> T.ofType(a : Any?) : T?{
    return runCatching {
        val classT = T::class.java
        classT.isInstance(a)
    }.getOrNull() as T
}

inline fun <reified  T> appendList(list1: MutableList<T>?,list2: MutableList<T>?) : MutableList<T>?{
    val list = mutableListOf<T>()
    list1?.let { list.addAll(list1) }
    list2?.let { list.addAll(list2) }
    return if(list.isEmpty()) null else list
}