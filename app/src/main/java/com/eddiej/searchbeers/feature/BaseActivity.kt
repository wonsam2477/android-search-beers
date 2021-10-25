package com.eddiej.searchbeers.feature

import android.os.Bundle
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

    protected var binding : B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResourceId()) as B
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}