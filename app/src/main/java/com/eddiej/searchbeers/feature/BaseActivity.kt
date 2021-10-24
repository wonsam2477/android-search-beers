package com.eddiej.searchbeers.feature

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    companion object {
        val TAG: String = this::class.java.simpleName
    }

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    protected val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutResourceId()) as B
    }
}