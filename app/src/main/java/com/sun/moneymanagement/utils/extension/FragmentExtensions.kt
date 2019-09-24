package com.sun.moneymanagement.utils.extension

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.getColorCompat(color: Int) = ContextCompat.getColor(context!!, color)

fun Fragment.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, text, duration).show()
}

fun Fragment.showToast(stringId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, this.getString(stringId), duration).show()
}

inline fun Fragment.setOnBackPressListener(crossinline action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
        }
    })
}

inline fun <reified T : Any> Fragment.argument(key: String, defaultValue: T? = null) = lazy {
    arguments?.get(key)  as? T ?: defaultValue
}

inline fun <reified T : Any> Fragment.argument(key: String) = lazy { arguments?.get(key) as T }


inline fun <T: Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
