package com.ash8h.barcodeshare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ash8h.barcodeshare.ui.main.MainFragment
import com.ash8h.barcodeshare.util.ext.replaceFragmentForSingleFragmentActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        if (savedInstanceState == null) {
            replaceFragmentForSingleFragmentActivity(MainFragment.newInstance())
        }
    }
}
