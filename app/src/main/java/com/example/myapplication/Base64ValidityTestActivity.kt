package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Partition.Companion.ofSize
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext


class Base64ValidityTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base64_validity_test)

        val users = mutableListOf<User>()
        for (i in 1..502) {
            users.add((User(id = i, name = "$i User")))
        }
        //users.clear()
        //val usersChunked = users.chunked(20)
        //val usersChunked = users.chunked(20)
        //val usersChunked = createChunks(users = users, chunkSize = 50)
        //val usersChunked = createChunksNew(users = users, chunkSize = 50)
        //val testMethod = testChunks(users = users, chunkSize = 50)
        //val usersChunked = users.createChunks(chunkSize = 0)
        //Log.d(TAG, "onCreate: $usersChunked $testMethod")
        val partition = ofSize(users, 3)
        val partition3 = users.ofSize(3)
        Log.d(TAG, "onCreate: $partition")

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
        chunkSize: Int = 10, context: CoroutineContext = Dispatchers.Main
    ): MutableMap<Int, MutableList<User>> {
        /*val scope = CoroutineScope(context + SupervisorJob())
        scope.launch {

        }*/
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
                chunks[1] = tempList
            }
            return chunks
        }
        while (totalChunkedSize != totalSize) {
            count++
            totalChunkedSize = count
            tempList.add(users[count - 1])
            //Log.d(TAG, "createChunksNew: $count $chunksCount")
            if (totalChunkedSize % chunkSize == 0) {
                chunks[count] = tempList
                tempList = mutableListOf()
                chunksCount += chunkSize
            }
            remainingItems = totalChunkedSize - chunksCount
        }
        val startIndex = users.size - remainingItems
        val lastIndex = users.size
        tempList = mutableListOf()
        for (i in startIndex until lastIndex) {
            tempList.add(users[i])
        }
        if (tempList.isNotEmpty()) {
            chunks[lastIndex] = tempList
        }
        return chunks
    }

    fun testChunks(users: MutableList<User>, chunkSize: Int = 3): MutableList<User> {
        val result = mutableListOf<User>()
        val counter = AtomicInteger()

        for (user in users) {
            if (counter.getAndIncrement() % chunkSize == 0) {
                result.add(user)
            }
            result.add(result.size - 1, user)
        }
        return result
    }
}

data class User(val id: Int, val name: String)