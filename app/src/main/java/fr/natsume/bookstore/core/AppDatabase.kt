package fr.natsume.bookstore.core

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.natsume.bookstore.book.Book
import fr.natsume.bookstore.book.BookDao

const val DATABASE_NAME = "book_store"

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}