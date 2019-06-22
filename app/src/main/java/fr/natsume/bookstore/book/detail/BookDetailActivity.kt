package fr.natsume.bookstore.book.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import fr.natsume.bookstore.R
import fr.natsume.bookstore.book.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
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
        viewModel.book.observe(this, Observer { book -> updateBook(book!!) })
    }

    private fun updateBook(book: Book) {
        Picasso.get()
            .load(book.pictureUrl)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(bookCover)
        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookSummary.text = book.summary
    }
}
