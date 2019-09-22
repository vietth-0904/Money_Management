package com.sun.moneymanagement.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    @LayoutRes
    @NonNull
    abstract fun getLayoutResource(): Int

    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    fun replaceChildFragment(@IdRes id: Int, fragment: Fragment, tag: String) {
        childFragmentManager.commit(allowStateLoss = true) {
            replace(id, fragment, tag)
        }
        childFragmentManager.executePendingTransactions()
    }

    fun removeChildFragment(tag: String) {
        val fragment = childFragmentManager.findFragmentByTag(tag)
        fragment?.let {
            childFragmentManager.commit(allowStateLoss = true) {
                remove(it)
            }
            childFragmentManager.executePendingTransactions()
        }
    }

    fun removeAllChildFragment() {
        for (fragment in childFragmentManager.fragments) {
            fragment?.let {
                childFragmentManager.commit(allowStateLoss = true) {
                    remove(it)
                }
                childFragmentManager.executePendingTransactions()
            }
        }
    }
}
