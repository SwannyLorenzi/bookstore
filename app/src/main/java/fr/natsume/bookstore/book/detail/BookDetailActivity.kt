package fr.natsume.bookstore.book.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
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

        const val REQUEST_DETAIL_BOOK = 1

        const val ACTION_DELETE_BOOK = "fr.natsume.bookstore.actions.ACTION_DELETE_BOOK"
    }

    private lateinit var viewModel: BookDetailViewModel
    private var bookId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        bookId = intent.getLongExtra(EXTRA_BOOK_ID, 1)
        Timber.d("Book id: $bookId")

        bookSummary.movementMethod = ScrollingMovementMethod()

        val factory = BookDetailViewModelFactory(bookId)
        viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)
        viewModel.book.observe(this, Observer { book -> if (book != null) updateBook(book) })
    }

    override fun onCreatePanelMenu(featureId: Int, menu: Menu): Boolean {
        menuInflater.inflate(R.menu.book_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                showConfirmDeleteBookDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun showConfirmDeleteBookDialog() {
        val confirmFragment = ConfirmDeleteBookDialogFragment.newInstance(bookTitle.text.toString())
        confirmFragment.listener = object: ConfirmDeleteBookDialogFragment.ConfirmDeleteDialogListener {
            override fun onDialogPositiveClick() {
                deleteBook()
            }

            override fun onDialogNegativeClick() {}
        }
        confirmFragment.show(supportFragmentManager, "confirmDeleteDialog")
    }

    private fun deleteBook() {
        intent = Intent(ACTION_DELETE_BOOK)
        intent.putExtra(EXTRA_BOOK_ID, bookId)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
