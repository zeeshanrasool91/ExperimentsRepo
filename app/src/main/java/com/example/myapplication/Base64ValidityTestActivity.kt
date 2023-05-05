package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Base64ValidityTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base64_validity_test)

        val users = mutableListOf<User>()
        for (i in 1..502) {
            users.add((User(id = i, name = "$i User")))
        }
        //val usersChunked = users.chunked(20)
        //val usersChunked = users.chunked(20)
        //val usersChunked = createChunks(users = users, chunkSize = 50)
        val usersChunked = createChunksNew(users = users, chunkSize = 50)
        //val usersChunked = users.createChunks(chunkSize = 0)
        Log.d(TAG, "onCreate: $usersChunked")
        /*Log.d(
            TAG,
            "onCreate: ${"wzuhjsk94ogekyahcahspmqvheha6k-o3avfnpsgax_jk4ll2hc=".decryptIfValid()}"
        )
        Log.d(
            TAG,
            "onCreate: ${"7jcerz27qy1c4fdf6w9xmx1egbc_pcxg2biqiu_pe0ucsfplemm=".decryptIfValid()}"
        )
        Log.d(TAG, "onCreate: ${"bdzaskyxndokzch-bnyvm0-6bliivlozomlpihiewdm=".decryptIfValid()}")

        Log.d(TAG, "onCreate: ${"rr1lzodpkjthq5lkrlobxyxlksotvb_3jtudjbkzkkm41rst".decryptIfValid()}")*/
    }

    private fun createChunks(
        users: MutableList<User>,
        chunkSize: Int = 10
    ): MutableList<MutableList<User>> {
        val chunks = mutableListOf<MutableList<User>>()
        val totalSize = users.size
        var totalChunkedSize = 0
        var count = 0
        var tempList = mutableListOf<User>()
        var remainingItems = 0
        var chunksCount = 0
        if (chunkSize == 0 || users.isEmpty()) {
            tempList = mutableListOf()
            if (users.isNotEmpty()) {
                tempList.addAll(users)
                chunks.add(tempList)
            }
            return chunks
        }
        while (totalChunkedSize != totalSize) {
            count++
            totalChunkedSize = count
            tempList.add(users[count - 1])
            if (totalChunkedSize % chunkSize == 0) {
                chunks.add(tempList)
                tempList = mutableListOf()
                chunksCount += chunkSize
            }
            remainingItems = totalChunkedSize - chunksCount
        }
        val startIndex = users.size - remainingItems
        tempList = mutableListOf()
        for (i in startIndex until users.size) {
            tempList.add(users[i])
        }
        if (tempList.isNotEmpty()) {
            chunks.add(tempList)
        }
        return chunks
    }

    private fun createChunksNew(
        users: MutableList<User>,
        chunkSize: Int = 10
    ): MutableMap<Int, MutableList<User>> {
        val chunks = mutableMapOf<Int, MutableList<User>>()
        val totalSize = users.size
        var totalChunkedSize = 0
        var count = 0
        var tempList = mutableListOf<User>()
        var remainingItems = 0
        var chunksCount = 0
        if (chunkSize == 0 || users.isEmpty()) {
            tempList = mutableListOf()
            if (users.isNotEmpty()) {
                tempList.addAll(users)
                chunks[0] = tempList
            }
            return chunks
        }
        while (totalChunkedSize != totalSize) {
            count++
            totalChunkedSize = count
            tempList.add(users[count - 1])
            if (totalChunkedSize % chunkSize == 0) {
                chunks[count] = tempList
                tempList = mutableListOf()
                chunksCount += chunkSize
            }
            remainingItems = totalChunkedSize - chunksCount
        }
        val startIndex = users.size - remainingItems
        tempList = mutableListOf()
        for (i in startIndex until users.size) {
            tempList.add(users[i])
        }
        if (tempList.isNotEmpty()) {
            chunks[startIndex + 1] = tempList
        }
        return chunks
    }
}

data class User(val id: Int, val name: String)