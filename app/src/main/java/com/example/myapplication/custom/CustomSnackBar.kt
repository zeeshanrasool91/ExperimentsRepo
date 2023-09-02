package com.example.myapplication.custom

import android.app.Activity
import android.graphics.PorterDuff
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.GeneralSnackBarLayoutBinding
import com.example.myapplication.getColorCompat
import com.example.myapplication.gone
import com.example.myapplication.visible
import com.google.android.material.snackbar.Snackbar

class CustomSnackBar<DB : ViewDataBinding>(
    val activity: Activity,
    @LayoutRes val layoutRes: Int,
    val view: View? = null,
    private val length: Int = Snackbar.LENGTH_SHORT,
    private val backgroundColor: Int? = activity.getColorCompat(android.R.color.transparent),
    private val backgroundTint: Int? = activity.getColorCompat(android.R.color.transparent),
    private var onBindingInitiated: ((customSnackBar: CustomSnackBar<DB>, binding: DB) -> Unit)? = null,
    var cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) {


    private val finalView = view ?: activity.findViewById(android.R.id.content)
    private val inflater: LayoutInflater = LayoutInflater.from(finalView.context)
    private val snackBar = Snackbar.make(finalView, "", length)
    private val snackBarLayout = snackBar.view as ViewGroup
    private lateinit var binding: DB
    var bindData: ((DB) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) {
                invokeBindData()
            }
        }


    init {
        initBinding()
        initView()
        onBindingInitiated()
    }

    private fun invokeBindData() {
        bindData?.invoke(binding)
    }

    private fun onBindingInitiated() {
        onBindingInitiated?.invoke(this@CustomSnackBar, binding)
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate(inflater, layoutRes, null, false)
    }

    private fun initView() {
        with(snackBarLayout) {
            removeAllViews()
            addView(binding.root)
            setPadding(0, 0, 0, 0)
            elevation = 0f
            setBackgroundColor(
                backgroundColor ?: finalView.context.getColorCompat(android.R.color.transparent)
            )
            snackBar.setBackgroundTint(
                backgroundTint ?: finalView.context.getColorCompat(android.R.color.transparent)
            )
        }
    }

    fun show() {
        if (activity.isFinishing.not() || activity.isDestroyed.not()) {
            snackBar.show()
        }
    }

    fun dismiss() {
        if (snackBar.isShown) {
            snackBar.dismiss()
        }
    }

    fun addCallBack() {
        if (activity.isFinishing.not() || activity.isDestroyed.not()) {
            snackBar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    if (event == DISMISS_EVENT_TIMEOUT) {
                        cancelFun?.invoke((activity.isFinishing || activity.isDestroyed), false)
                    }
                }

                override fun onShown(sb: Snackbar?) {
                    super.onShown(sb)
                }

            })
        }
    }

    fun getSnackBar(): Snackbar {
        return snackBar
    }

    fun getSnackBarLayout(): ViewGroup {
        return snackBarLayout
    }

    companion object {
        fun DialogFragment.showGeneralSnackBar(
            view: View? = this.dialog?.window?.findViewById(android.R.id.content),
            message: String? = null,
            length: Int = -3,
            snackBar: SnackBar = SnackBar.SUCCESS,
            actionLabel: String? = null,
            actionLabelTextColor: Int? = null,
            cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
        ) =
            activity?.showGeneralSnackBar(
                view = view,
                message = message,
                length = length,
                snackBar = snackBar,
                actionLabel = actionLabel,
                actionLabelTextColor = actionLabelTextColor,
                cancelFun = cancelFun
            )

        fun Fragment.showGeneralSnackBar(
            view: View? = null,
            message: String? = null,
            length: Int = -3,
            snackBar: SnackBar = SnackBar.SUCCESS,
            actionLabel: String? = null,
            actionLabelTextColor: Int? = null,
            cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
        ) =
            if (this.parentFragment is DialogFragment) {
                val dialogFragment = this.parentFragment as DialogFragment
                dialogFragment.showGeneralSnackBar(
                    view ?: dialogFragment.dialog?.window?.findViewById(android.R.id.content),
                    message,
                    length,
                    snackBar,
                    actionLabelTextColor = actionLabelTextColor,
                    cancelFun = cancelFun
                )
            } else {
                activity?.showGeneralSnackBar(
                    view = view,
                    message = message,
                    length = length,
                    snackBar = snackBar,
                    actionLabel = actionLabel,
                    actionLabelTextColor = actionLabelTextColor,
                    cancelFun = cancelFun
                )
            }


        fun Activity.showGeneralSnackBar(
            view: View? = null,
            message: String? = null,
            length: Int = -3,
            snackBar: SnackBar = SnackBar.SUCCESS,
            actionLabel: String? = null,
            actionLabelTextColor: Int? = null,
            cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
        ) {

            /*<GeneralSnackBarLayoutBinding>*/
            CustomSnackBar(
                activity = this@showGeneralSnackBar,
                layoutRes = R.layout.general_snack_bar_layout,
                view = view,
                length = length,
                backgroundColor = this.getColorCompat(android.R.color.transparent),
                backgroundTint = this.getColorCompat(android.R.color.transparent),
                cancelFun = cancelFun,
                onBindingInitiated = { customSnackBar, binding ->
                    onBindData(
                        binding = binding,
                        message = message,
                        snackBar = snackBar,
                        activity = this@showGeneralSnackBar,
                        cancelFun = cancelFun,
                        customSnackBar = customSnackBar,
                        actionLbl = actionLabel,
                        actionLabelTextColor = actionLabelTextColor
                    )
                }
            ).apply {
                /*this.bindData = { binding ->
                    Log.d("SNACK_BAR_TEST", "showGeneralSnackBar Init:  $binding")
                    onBindData(
                        binding = binding,
                        message = message,
                        snackBarType = snackBarType,
                        activity = this@showGeneralSnackBar,
                        cancelFun = cancelFun,
                        customSnackBar = customSnackBar,
                        actionLabel = actionLabel,
                        actionLabelTextColor = actionLabelTextColor
                    )
                }*/
                show()
                addCallBack()
            }

        }

        private fun onBindData(
            binding: GeneralSnackBarLayoutBinding,
            message: String?,
            snackBar: SnackBar,
            activity: Activity,
            cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)?,
            customSnackBar: CustomSnackBar<GeneralSnackBarLayoutBinding>,
            actionLbl: String?,
            actionLabelTextColor: Int?
        ) {
            binding.imgStatus.setImageResource(snackBar.getIcon())
            binding.cardViewParent.setCardBackgroundColor(
                activity.getColorCompat(
                    snackBar.cardBackgroundColor
                )
            )

            binding.txtMessage.text =
                if (TextUtils.isEmpty(message)) activity.getString(snackBar.getMessage()) else message
            binding.txtMessage.setTextColor(activity.getColorCompat(snackBar.getTextColor()))
            binding.imgClose.setColorFilter(
                activity.getColorCompat(snackBar.getTextColor()),
                PorterDuff.Mode.SRC_ATOP
            )
            /*binding.imgClose.setTint(snackBarType.textColor())*/
            val actionType = when (snackBar) {
                is SnackBar.CUSTOM -> {
                    snackBar.setActionType
                }

                else -> {
                    snackBar.getActionType()
                }
            }
            when (actionType) {
                SnackBar.ACTION_CLOSE -> {
                    binding.imgClose.visible()
                    binding.tvActionLbl.gone()
                    binding.btnUndo.gone()
                    binding.imgClose.setOnClickListener {
                        cancelFun?.invoke(
                            (activity.isFinishing || activity.isDestroyed),
                            true
                        )
                        customSnackBar.dismiss()
                    }
                }
                SnackBar.ACTION_BUTTON -> {
                    binding.btnUndo.visible()
                    binding.imgClose.gone()
                    binding.tvActionLbl.gone()
                    binding.btnUndo.setOnClickListener {
                        cancelFun?.invoke(
                            (activity.isFinishing || activity.isDestroyed),
                            true
                        )
                        customSnackBar.dismiss()
                    }

                }
                SnackBar.ACTION_LABEL -> {
                    binding.tvActionLbl.visible()
                    binding.imgClose.gone()
                    binding.btnUndo.gone()
                    binding.tvActionLbl.text = actionLbl
                    binding.tvActionLbl.setTextColor(
                        actionLabelTextColor
                            ?: activity.getColorCompat(snackBar.getActionTextColor())
                    )
                    binding.tvActionLbl.setOnClickListener {
                        cancelFun?.invoke(
                            (activity.isFinishing || activity.isDestroyed),
                            true
                        )
                        customSnackBar.dismiss()
                    }
                }
            }
        }
    }
}