package com.ash8h.barcodeshare.util.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes textResId: Int) {
    context?.let { context ->
        toast(context.getString(textResId))
    }
}

fun Fragment.toast(text: CharSequence) {
    context?.let { context ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.showKeyboard(view: View) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(view, 0)
}

fun Fragment.hideKeyboard() {
    val windowToken = view?.windowToken ?: return
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}
