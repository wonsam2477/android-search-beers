package com.eddiej.searchbeers.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    companion object {
        val TAG: String = this::class.java.simpleName
    }

    protected var binding: B? = null

    private val _compositeDisposable = CompositeDisposable()

    // 초기화 진행 로직 (Subscribe, Observe, Adapter 초기화 등)
    abstract fun setupViews()

    // 뷰의 갱신이 필요한 로직 (View update, data reload 등)
    abstract fun bindViews()

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        binding?.lifecycleOwner = viewLifecycleOwner

        return binding?.root
    }

    override fun onDestroyView() {
        _compositeDisposable.dispose()
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    fun getDisposable(): CompositeDisposable = _compositeDisposable
    fun addDisposable(disposable: Disposable) {
        _compositeDisposable.add(disposable)
    }
}