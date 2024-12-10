package com.example.cryptocurrencyexchange.domain

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
class MyWorkerFactory( private val repository: Repository ) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters ): ListenableWorker? {
        return when (workerClassName) {
            MyCoroutineWorker::class.java.name ->
                MyCoroutineWorker(appContext, workerParameters, repository)
            else -> null
        }
    }
}