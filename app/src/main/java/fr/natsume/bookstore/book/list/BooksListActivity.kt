package fr.natsume.bookstore.book.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fr.natsume.bookstore.R
import fr.natsume.bookstore.book.Book
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

        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)
        viewModel.books.observe(this, Observer { newBooks -> updateBooks(newBooks!!) })
    }

    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("Loading new books: $newBooks")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
    }

    override fun onBookSelected(book: Book) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
