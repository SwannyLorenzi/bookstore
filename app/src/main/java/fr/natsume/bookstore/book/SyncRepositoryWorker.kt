package fr.natsume.bookstore.book

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import fr.natsume.bookstore.core.App
import timber.log.Timber

class SyncRepositoryWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Timber.i("Synchronizing books...")

        val bookApi = FakeBookApi()
        val bookDao = App.db.bookDao()

        bookDao.insertBooks(bookApi.loadBooks())

        return Result.success()
    }
}