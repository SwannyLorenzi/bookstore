package fr.natsume.bookstore.book

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBookById(id: Long) : LiveData<Book>

    @Insert
    fun insertBooks(books: List<Book>)
}