package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Base64ValidityTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base64_validity_test)

        Log.d(
            TAG,
            "onCreate: ${"wzuhjsk94ogekyahcahspmqvheha6k-o3avfnpsgax_jk4ll2hc=".decryptIfValid()}"
        )
        Log.d(
            TAG,
            "onCreate: ${"7jcerz27qy1c4fdf6w9xmx1egbc_pcxg2biqiu_pe0ucsfplemm=".decryptIfValid()}"
        )
        Log.d(TAG, "onCreate: ${"bdzaskyxndokzch-bnyvm0-6bliivlozomlpihiewdm=".decryptIfValid()}")

        Log.d(TAG, "onCreate: ${"rr1lzodpkjthq5lkrlobxyxlksotvb_3jtudjbkzkkm41rst".decryptIfValid()}")
    }
}