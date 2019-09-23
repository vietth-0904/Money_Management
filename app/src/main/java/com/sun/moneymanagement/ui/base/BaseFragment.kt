package com.sun.moneymanagement.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

abstract class BaseFragment : Fragment() {

    abstract val layoutResourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResourceId, container,false);
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
