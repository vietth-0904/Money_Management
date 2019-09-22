package com.sun.moneymanagement.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.sun.moneymanagement.R

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutResourceId: Int

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this
    }

    fun addFragment(@IdRes id: Int, fragment: Fragment, tag: String) {
        supportFragmentManager.apply {
            commit(allowStateLoss = true) {
                setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.enter_from_right,
                    R.anim.exit_to_right,
                    R.anim.exit_to_right
                )
                add(id, fragment, tag)
                addToBackStack(null)
            }
            executePendingTransactions()
        }
    }

    fun replaceFragment(
        @IdRes id: Int,
        fragment: Fragment,
        tag: String,
        animResEnter: Int = R.anim.enter_from_right,
        animResExit: Int = R.anim.exit_to_right
    ) {
        supportFragmentManager.apply {
            commit(allowStateLoss = true) {
                setCustomAnimations(animResEnter, animResEnter, animResExit, animResExit)
                add(id, fragment, tag)
                addToBackStack(null)
            }
            executePendingTransactions()
        }
    }
}
