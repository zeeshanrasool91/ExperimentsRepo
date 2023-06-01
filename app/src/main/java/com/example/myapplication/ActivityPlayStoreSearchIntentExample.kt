package com.example.myapplication

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityPlayStoreSearchIntentExampleBinding
import com.google.common.collect.ImmutableSet
import com.google.common.collect.Sets


class ActivityPlayStoreSearchIntentExample : AppCompatActivity() {


    private lateinit var binding: ActivityPlayStoreSearchIntentExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_play_store_search_intent_example
        )
        val dates = mutableListOf<Long>()
        for (i in 0..5) {
            dates.add(TimeUtils.getDates(i))
        }
        binding.btnSearchOnPlayStore.setOnClickListener {
            /*if (binding.tilSearch.editText?.text.isNullOrEmpty()) {
                binding.tilSearch.error = "Please enter name"
                return@setOnClickListener
            }*/
            /*this@ActivityPlayStoreSearchIntentExample.searchInGooglePlay(
                binding.tilSearch.editText?.text?.trim().toString()
            )*/
            /*this@ActivityPlayStoreSearchIntentExample.openPlayStore(
                "com.reasonlabs.familykeeper.parentalcontrol.parents"
            )*/
            dates.forEach {
                Log.d(TAG, "onCreate123: ${TimeUtils.formatDate(it)}")
            }
        }

        for (i in 0..5) {
            dates.add(TimeUtils.getDates(i))
        }

        val subSet = SubSet()
        val words ="Hello Zeeshan How are you".split(" ")
        //Log.d(TAG, "SUBSETEXAMPEL: ${subSet.subsets(arrayListOf("ZEE", "SHA", "N"))}")
        Log.d(TAG, "SUBSETEXAMPEL: ${subSet.subsets(words)}")
    }
}

fun Context.searchInGooglePlay(searchQuery: String?) {
    if (searchQuery == null)
        return
    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW, Uri
                    .parse("market://search?q=$searchQuery")
            )
        )
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW, Uri
                    .parse(
                        "http://play.google.com/store/search?q=$searchQuery"
                    )
            )
        )
    }
}

fun Context.openPlayStore(packageName: String?) {
    if (packageName == null) {
        return
    }
    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")
            )
        )
    } catch (e: ActivityNotFoundException) {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}