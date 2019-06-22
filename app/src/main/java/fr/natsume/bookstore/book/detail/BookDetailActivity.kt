package fr.natsume.bookstore.book.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import fr.natsume.bookstore.R
import timber.log.Timber

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BOOK_ID = "bookId"
    }

    private lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookId = intent.getLongExtra(EXTRA_BOOK_ID, 1)
        Timber.d("Book id: $bookId")

        val factory = BookDetailViewModelFactory(bookId)
        viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)
    }
}
