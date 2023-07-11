package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityPlayStoreSearchIntentExampleBinding


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

        binding.switch1.setOnCheckChangeListener(1000L) { buttonView, isChecked ->
            //Log.d(TAG, "setOnCheckChangeListener: $buttonView $isChecked")
            val sentence = "i get money i love it i want to buy something"
            val words = sentence.split(" ")
            /*val list = Generator.combination(words).simple(2)
            list.forEach {
                Log.d(TAG, "SUBSETEXAMPEL: $it")
            }*/


            //val len = words.size
            //var temp = 0
            //Total possible subsets for string of size n is n*(n+1)/2
            //Total possible subsets for string of size n is n*(n+1)/2
            //val arr = arrayOfNulls<String>(len * (len + 1) / 2)

            //This loop maintains the starting character

            //This loop maintains the starting character
            //for (i in 0 until len) {
                //This loop adds the next character every iteration for the subset to form and add it to the array
                //for (j in i until len) {
                    //arr[temp] = words[i]
                    //temp++
                //}
            //}
            val subSet = SubSet()
            val subsets = subSet.subsets(words = words, maxIteration = 2)

            Log.d(TAG, "onCreate: $subsets")

            val subSetUsingHashSet = SubSetUsingHashSet()
            val arr = subSetUsingHashSet.subsets(words = words, maxIteration = 2)
            Log.d(TAG, "onCreate: $arr")

        }
        binding.btnSearchOnPlayStore.setOnClickListener(debounceInterval = 1000L) {
            //val searchString = "ילד דובי"
            //val words = searchString.split("\\P{L}+".toRegex())
            //Log.d(TAG, "btnSearchOnPlayStore: $words")
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
            /*dates.forEach {
                //Log.d(TAG, "onCreate123: ${TimeUtils.formatDate(it)}")
            }*/
        }

        /*for (i in 0..5) {
            dates.add(TimeUtils.getDates(i))
        }*/

        //val subSet = SubSet()
        //val words = "Hello Zeeshan How are you".split(" ")
        //Log.d(TAG, "SUBSETEXAMPEL: ${subSet.subsets(arrayListOf("ZEE", "SHA", "N"))}")
        //Log.d(TAG, "SUBSETEXAMPEL: ${subSet.subsets(words)}")
        /*val dom: List<Int> = mutableListOf(1, 2, 3, 4, 5)
        val list = Generator.combination(words)
            .simple(2)
        //.forEach(System.out::println)
        *//*.forEach(System.out::println)*//*
        list.forEach {
            Log.d(TAG, "SUBSETEXAMPEL: $it")
        }*/

        //val generateAllSubsetsOfSizeK = GenerateAllSubsetsOfSizeK()
        //val superSet: MutableList<String> = ArrayList()
        //superSet.addAll(words)
        //Log.d(TAG, "SUBSETEXAMPEL: ${generateAllSubsetsOfSizeK.getSubsets(superSet, 2)}")
        //Log.d(TAG, "SUBSETEXAMPEL: ${generateAllSubsetsOfSizeK.getSubsets(superSet, 2)}")
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