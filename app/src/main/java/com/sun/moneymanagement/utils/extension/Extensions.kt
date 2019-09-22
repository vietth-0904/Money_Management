package com.sun.moneymanagement.utils.extension

import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.widget.EditText

inline fun postDelay(time: Long = 0, crossinline block: () -> Unit) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({ block() }, time)
}

fun String.isPhone(): Boolean
        = Patterns.PHONE.matcher(this).matches() && this.length >= 10 && this.length <= 14

fun String.isEmail(): Boolean {
    val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
    return matches(p)
}

fun CharSequence.isPhone(): Boolean
        = Patterns.PHONE.matcher(this).matches() && this.length >= 10 && this.length <= 14

fun <T : Any> T.TAG() = this::class.java.simpleName.toString()

fun EditText.setEndSelection() {
    setSelection(text.length)
}

fun ArrayList<EditText>.handleEditTexts(actionAfterLastEditText: () -> Unit?) {
    for (i in this.indices) {
        this[i].setOnFocusChangeListener { _, hasFocus ->
            run {
                if (!hasFocus) {
                    var flag = false
                    for (et in this) {
                        if (et.isFocused) flag = true
                    }
                    if (!flag) this[i].hideKeyboard()
                }
            }
        }

        this[i].setOnEditorActionListener { _, actionId, _ ->
            run {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (i < (this.size - 1)) {
                        this[i + 1].requestFocus()
                        this[i + 1].setEndSelection()
                    } else {
                        this[i].hideKeyboard()
                        actionAfterLastEditText()
                    }
                }
                false
            }
        }
    }
}
