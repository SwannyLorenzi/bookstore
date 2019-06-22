package fr.natsume.bookstore.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.natsume.bookstore.book.Book
import fr.natsume.bookstore.book.BookDao

const val DATABASE_NAME = "book_store"

@Database(entities = [Book::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}

val MIGRATION_1_2 = object: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DELETE FROM book;")
        database.execSQL("CREATE UNIQUE INDEX ux_book_title ON book(title);")
    }
}