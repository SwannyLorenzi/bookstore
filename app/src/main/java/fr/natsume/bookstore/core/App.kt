package fr.natsume.bookstore.core

import android.app.Application
import androidx.room.Room

class App : Application() {

    companion object {
        lateinit var db : AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).build()
    }
}