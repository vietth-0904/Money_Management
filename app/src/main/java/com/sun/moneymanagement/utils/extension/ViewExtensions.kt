package com.sun.moneymanagement.utils.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.sun.moneymanagement.utils.SafeClickListener

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.showIf(condition: Boolean): View {
    if (visibility != View.VISIBLE && condition) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
