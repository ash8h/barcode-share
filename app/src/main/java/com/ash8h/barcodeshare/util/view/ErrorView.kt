package com.ash8h.barcodeshare.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.ash8h.barcodeshare.R
import com.ash8h.barcodeshare.util.getErrorMessage
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ErrorView, defStyleAttr, 0)
        val hasTitle = typedArray.getBoolean(R.styleable.ErrorView_has_title, true)
        typedArray.recycle()

        addView(View.inflate(context, R.layout.view_error, null))
        isClickable = true
        retry_button.visibility = View.GONE
        if (hasTitle.not()) {
            icon.visibility = View.GONE
            title_text.visibility = View.GONE
        }
    }

    var visible: Boolean
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
            Unit
        }

    var title: CharSequence?
        get() = findViewById<TextView>(R.id.title_text).text
        set(value) {
            findViewById<TextView>(R.id.title_text).text = value
        }

    var message: CharSequence?
        get() = findViewById<TextView>(R.id.message).text
        set(value) {
            findViewById<TextView>(R.id.message).text = value
        }

    var onRetryClicked: (() -> Unit)? = null
        set(value) {
            field = value
            retry_button.setOnClickListener { value?.invoke() }
            retry_button.visibility = if (value == null) View.GONE else View.VISIBLE
        }

    var error: Throwable
        get() = throw IllegalStateException("The operation is not allowed")
        set(value) {
            message = getErrorMessage(context, value)
        }
}
