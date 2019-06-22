package fr.natsume.bookstore.book.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.natsume.bookstore.book.Book
import fr.natsume.bookstore.core.App

class BookDetailViewModel(bookId: Long) : ViewModel() {

    private val bookIdLiveData = MutableLiveData<Long>()

    val bookLiveData: LiveData<Book> = Transformations.switchMap(bookIdLiveData) { id ->
        App.db.bookDao().getBookById(id)
    }

    init {
        bookIdLiveData.value = bookId
    }
}