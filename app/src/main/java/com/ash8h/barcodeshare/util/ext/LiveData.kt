package com.ash8h.barcodeshare.util.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observe: (value: T) -> Unit) = apply {
    observe(owner, Observer { value ->
        value ?: return@Observer
        observe(value)
    })
}
