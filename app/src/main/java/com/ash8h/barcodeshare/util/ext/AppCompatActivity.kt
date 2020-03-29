package com.ash8h.barcodeshare.util.ext

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.ash8h.barcodeshare.R

fun AppCompatActivity.replaceFragmentForSingleFragmentActivity(fragment: Fragment, enableSlideAnimation: Boolean = false) {
    supportFragmentManager.beginTransaction().apply {
        if (enableSlideAnimation) {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_out)
        }
    }
        .replace(R.id.fragment_container, fragment)
        .commit()
}

fun AppCompatActivity.setupToolbar(@StringRes resId: Int) {
    setupToolbar(getString(resId))
}

fun AppCompatActivity.setupToolbar(title: String) {
    findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        supportActionBar?.title = title
    }
}

fun AppCompatActivity.finish(resultCode: Int) {
    setResult(resultCode)
    finish()
}
