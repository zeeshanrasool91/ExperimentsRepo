package com.example.myapplication.custom

import android.app.Activity
import android.graphics.PorterDuff
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomConfirmationSnackbarBinding
import com.example.myapplication.databinding.CustomSnackbarBinding
import com.example.myapplication.getColorCompat
import com.example.myapplication.invisible
import com.example.myapplication.isNotNullNorEmpty
import com.example.myapplication.visible
import com.google.android.material.snackbar.Snackbar

//region SnackBar Extension for Activity,Fragment and DialogFragment this section also contains the code which is related to undoSnackBar
fun DialogFragment.showSnackBar(
    view: View? = this.dialog?.window?.findViewById(android.R.id.content),
    message: String? = null,
    length: Int = -3,
    snackBarType: SnackBarType = SnackBarType.SUCCESS,
    actionLbl: String? = null,
    cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) =
    activity?.showSnackBar(
        view = view,
        message = message,
        length = length,
        snackBarType = snackBarType,
        actionLbl = actionLbl,
        cancelFun = cancelFun
    )

fun Fragment.showSnackBar(
    view: View? = null,
    message: String? = null,
    length: Int = -3,
    snackBarType: SnackBarType = SnackBarType.SUCCESS,
    actionLbl: String? = null,
    cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) =
    if (this.parentFragment is DialogFragment) {
        val dialogFragment = this.parentFragment as DialogFragment
        dialogFragment.showSnackBar(
            view ?: dialogFragment.dialog?.window?.findViewById(android.R.id.content),
            message,
            length,
            snackBarType,
            cancelFun = cancelFun
        )
    } else {
        activity?.showSnackBar(
            view = view,
            message = message,
            length = length,
            snackBarType = snackBarType,
            actionLbl = actionLbl,
            cancelFun = cancelFun
        )
    }


fun Activity.showSnackBar(
    view: View? = null,
    message: String? = null,
    length: Int = -3,
    snackBarType: SnackBarType = SnackBarType.SUCCESS,
    actionLbl: String? = null,
    cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) {


    CustomSnackBar(
        activity = this@showSnackBar,
        layoutRes = R.layout.custom_snackbar,
        view = view,
        length = length,
        backgroundColor = this.getColorCompat(android.R.color.transparent),
        backgroundTint = this.getColorCompat(android.R.color.transparent),
        cancelFun = cancelFun,
        onBindingInitiated = { customSnackBar, binding ->
            Log.d("SNACK_BAR_TEST", "showSnackBar Init: $customSnackBar $binding")
            bindData(
                binding = binding,
                message = message,
                snackBarType = snackBarType,
                activity1 = this@showSnackBar,
                cancelFun = cancelFun,
                customSnackBar = customSnackBar,
                actionLbl = actionLbl
            )
        }
    ).apply {
        /*this.bindData = { binding ->
            bindData(
                binding = binding,
                message = message,
                snackBarType = snackBarType,
                activity1 = this@showSnackBar,
                cancelFun = cancelFun,
                customSnackBar = this,
                actionLbl = actionLbl
            )
        }*/
        show()
        addCallBack()
    }

}

private fun bindData(
    binding: CustomSnackbarBinding,
    message: String?,
    snackBarType: SnackBarType,
    activity1: Activity,
    cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)?,
    customSnackBar: CustomSnackBar<CustomSnackbarBinding>,
    actionLbl: String?
) {
    binding.txtMessage.text = message
    binding.imgStatus.setImageResource(snackBarType.getIcon())
    binding.cardViewParent.setCardBackgroundColor(
        activity1.getColorCompat(
            snackBarType.cardBackgroundColor
        )
    )
    binding.txtMessage.text =
        if (TextUtils.isEmpty(message)) activity1.getString(snackBarType.getMessage()) else message
    binding.txtMessage.setTextColor(activity1.getColorCompat(snackBarType.textColor()))
    binding.imgClose.setColorFilter(
        activity1.getColorCompat(snackBarType.textColor()),
        PorterDuff.Mode.SRC_ATOP
    )
    binding.imgClose.setOnClickListener {
        cancelFun?.invoke(
            (activity1.isFinishing || activity1.isDestroyed),
            true
        )
        customSnackBar.dismiss()
    }

    if (actionLbl.isNotNullNorEmpty()) {
        binding.tvActionLbl.visible()
        binding.imgClose.invisible()
        binding.tvActionLbl.text = actionLbl
    }

    binding.tvActionLbl.setOnClickListener {
        cancelFun?.invoke(
            (activity1.isFinishing || activity1.isDestroyed),
            true
        )
        customSnackBar.dismiss()
    }
}

fun DialogFragment.showUndoSnackBar(
    view: View? = this.dialog?.window?.findViewById(android.R.id.content),
    message: String? = null,
    length: Int = Snackbar.LENGTH_SHORT,
    cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) =
    activity?.showUndoSnackBar(view, message, length, cancelFun)

fun Fragment.showUndoSnackBar(
    view: View? = null,
    message: String? = null,
    length: Int = Snackbar.LENGTH_SHORT,
    cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) =
    if (this.parentFragment is DialogFragment) {
        val dialogFragment = this.parentFragment as DialogFragment
        dialogFragment.showUndoSnackBar(
            view ?: dialogFragment.dialog?.window?.findViewById(android.R.id.content),
            message,
            length,
            cancelFun
        )
    } else {
        activity?.showUndoSnackBar(view, message, length, cancelFun)
    }

fun Activity.showUndoSnackBar(
    view: View? = null,
    message: String? = null,
    length: Int = Snackbar.LENGTH_SHORT,
    cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) {
    CustomSnackBar<CustomConfirmationSnackbarBinding>(
        activity = this@showUndoSnackBar,
        layoutRes = R.layout.custom_confirmation_snackbar,
        view = view,
        length = length,
        backgroundColor = this.getColorCompat(android.R.color.transparent),
        backgroundTint = this.getColorCompat(android.R.color.transparent)
    ).apply {
        this.cancelFun = cancelFun
        this.bindData = { binding ->
            binding.txtMessage.text = message
            binding.btnUndo.setOnClickListener {
                cancelFun?.invoke(
                    this.activity.isFinishing || this.activity.isDestroyed,
                    true
                )
                dismiss()
            }
        }
        show()
    }
}
//endregion