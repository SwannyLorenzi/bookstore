package fr.natsume.bookstore.book

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM book ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBookById(id: Long) : LiveData<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<Book>)

    @Delete
    fun deleteBook(book: Book)

    @Query("DELETE FROM book WHERE id = :id")
    fun deleteBookById(id: Long)
}