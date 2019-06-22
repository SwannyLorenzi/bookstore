package fr.natsume.bookstore.book.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.natsume.bookstore.book.Book
import fr.natsume.bookstore.core.App
import java.util.concurrent.Executors

class BooksListViewModel : ViewModel() {

    val books: LiveData<List<Book>> = App.db.bookDao().getAllBooks()

    fun refreshBooks() {
        App.repository.syncNow()
    }

    fun deleteBook(id: Long) {
        Executors.newSingleThreadExecutor().execute {
            App.db.bookDao().deleteBookById(id)
        }
    }
}
