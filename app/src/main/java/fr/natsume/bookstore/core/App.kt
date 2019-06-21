package fr.natsume.bookstore.core

import android.app.Application
import androidx.room.Room
import fr.natsume.bookstore.book.BookRepository
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var db : AppDatabase
        lateinit var repository : BookRepository
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        db = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).build()

        repository = BookRepository()
        repository.scheduleSync()
    }
}