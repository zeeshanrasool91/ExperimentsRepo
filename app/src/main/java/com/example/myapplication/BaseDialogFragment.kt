package com.example.myapplication

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open class BaseDialogFragment<T : ViewBinding>(private val inflate: Inflate<T>/*private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T*/) :
    AppCompatDialogFragment() {
    var _binding: T? = null
    val binding: T get() = _binding ?: error("Must only access binding while fragment is attached.")

    protected var isBottom: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //_binding = bindingInflater(inflater, container, false)
        //_binding = bindingInflater.invoke(inflater, container, false)
        //_binding = inflate.invoke(inflater, container, false)
        Log.d(TAG, "onCreateView:")
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        super.onViewCreated(view, savedInstanceState)
        this.lifecycle.addObserver(mObserver)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (context != null && isBottom) {
            BottomSheetDialog(requireContext())
        } else {
            /*return activity?.let {
                val builder = MaterialAlertDialogBuilder(it)

                // Get the layout inflater
                val inflater = requireActivity().layoutInflater
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                _binding = inflate(inflater, null, false)

                builder.setView(binding.root)

                val dialog = builder.create()

                val back = ColorDrawable(Color.TRANSPARENT)
                val inset = InsetDrawable(back, 40)
                dialog.window?.setBackgroundDrawable(inset)

                dialog
            } ?: */ super.onCreateDialog(
                savedInstanceState
            )

        }
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    private fun prepareDialog(){
        activity?.let {
            val builder = MaterialAlertDialogBuilder(it)

            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            _binding = inflate(inflater, null, false)

            builder.setView(binding.root)

            val dialog = builder.create()

            val back = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(back, 40)
            dialog.window?.setBackgroundDrawable(inset)
            dialog
        }
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

    private val mObserver =
        LifecycleEventObserver { source, event ->
            Log.d(TAG, "$source : $event")
        }

}