package com.example.cryptocurrencyexchange.domain

import android.content.Context
import android.util.Log.d
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyCoroutineWorker(context: Context, params: WorkerParameters, private val repository : Repository)
    : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                repository.fetchData()
                d("XXXX", "Fetching data...")
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}

