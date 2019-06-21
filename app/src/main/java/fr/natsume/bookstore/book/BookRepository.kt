package fr.natsume.bookstore.book

import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BookRepository {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun syncNow() {
        Timber.i("Synchronizing books now")
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .beginUniqueWork("syncBooksNow", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }

    fun scheduleSync() {
        Timber.i("Scheduling books sync")
        val work = PeriodicWorkRequestBuilder<SyncRepositoryWorker>(12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .enqueueUniquePeriodicWork("syncBooksScheduled", ExistingPeriodicWorkPolicy.KEEP, work)
    }
}