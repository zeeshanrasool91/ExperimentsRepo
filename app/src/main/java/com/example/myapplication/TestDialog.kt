package com.example.myapplication

import com.example.myapplication.databinding.DummyLayoutBinding

class TestDialog : BaseDialogFragment<DummyLayoutBinding>(DummyLayoutBinding::inflate) {

    companion object {
        fun instance(): TestDialog {
            return TestDialog()
        }
    }
}