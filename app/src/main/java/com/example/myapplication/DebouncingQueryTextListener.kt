package com.example.myapplication

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    private val debouncePeriod: Long = 500,
    private val onDebouncingQueryTextChange: (String?) -> Unit
    ) : TextWatcher {


        private val coroutineScope = lifecycle.coroutineScope

        private var searchJob: Job? = null

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                s?.toString()?.let {
                    delay(debouncePeriod)
                    onDebouncingQueryTextChange(it)
                }
            }
        }
    }