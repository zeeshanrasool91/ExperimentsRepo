package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open class BaseDialogFragment<T: ViewBinding>(private val inflate: Inflate<T>/*private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T*/)
    : AppCompatDialogFragment() {
    var _binding: T? = null
    val binding: T get() = _binding ?: error("Must only access binding while fragment is attached.")

    protected var isBottom: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //_binding = bindingInflater(inflater, container, false)
        //_binding = bindingInflater.invoke(inflater, container, false)
        //_binding = inflate.invoke(inflater, container, false)
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    final override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (context != null && isBottom) BottomSheetDialog(requireContext()) else super.onCreateDialog(savedInstanceState)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun <T : ViewBinding> newInstance2(bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T): BaseDialogFragment<T> {
            return BaseDialogFragment(bindingInflater)
        }
    }

}