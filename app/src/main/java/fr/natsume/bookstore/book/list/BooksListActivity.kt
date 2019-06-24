package fr.natsume.bookstore.book.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fr.natsume.bookstore.R
import fr.natsume.bookstore.book.Book
import fr.natsume.bookstore.book.detail.BookDetailActivity
import kotlinx.android.synthetic.main.activity_books_list.*
import timber.log.Timber

class BooksListActivity : AppCompatActivity(), BooksListAdapter.BooksListAdapterListener {

    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksListAdapter
    private lateinit var books: MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list)

        books = mutableListOf()
        booksAdapter = BooksListAdapter(books, this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BooksListActivity)
            adapter = booksAdapter
        }

        swipeRefresh.setOnRefreshListener { viewModel.refreshBooks() }

        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)
        viewModel.books.observe(this, Observer { newBooks -> updateBooks(newBooks!!) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null) {
            return
        }

        when (requestCode) {
            BookDetailActivity.REQUEST_DETAIL_BOOK -> processDetailBookResult(data)
        }
    }

    private fun processDetailBookResult(data: Intent) {
        val bookId = data.getLongExtra(BookDetailActivity.EXTRA_BOOK_ID, -1)
        when (data.action) {
            BookDetailActivity.ACTION_DELETE_BOOK -> {
                deleteBook(bookId)
            }
        }
    }

    private fun deleteBook(bookId: Long) {
        if (bookId < 0 ) return
        viewModel.deleteBook(bookId)
        Toast.makeText(this, "Book $bookId deleted.", Toast.LENGTH_SHORT).show()
    }

    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("Loading new books. ${newBooks.size} books loaded.")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    override fun onBookSelected(book: Book) {
        Timber.d("Select book: $book")
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, book.id)
        startActivityForResult(intent, BookDetailActivity.REQUEST_DETAIL_BOOK)
    }
}
