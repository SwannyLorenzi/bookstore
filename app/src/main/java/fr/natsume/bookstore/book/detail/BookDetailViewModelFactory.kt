package fr.natsume.bookstore.book.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookDetailViewModelFactory(private val bookId: Long)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return BookDetailViewModel(bookId) as T
    }
}