package fr.natsume.bookstore.book.detail

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import fr.natsume.bookstore.R

class ConfirmDeleteBookDialogFragment : DialogFragment() {

    companion object {
        const val EXTRA_BOOK_TITLE = "bookTitle"

        fun newInstance(noteTitle: String): ConfirmDeleteBookDialogFragment {
            val fragment = ConfirmDeleteBookDialogFragment()
            fragment.arguments = Bundle().apply { putString(EXTRA_BOOK_TITLE, noteTitle) }
            return fragment
        }
    }

    interface ConfirmDeleteDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    var listener: ConfirmDeleteDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage(getString(R.string.confirm_delete_book, arguments!!.getString(EXTRA_BOOK_TITLE)))
            .setPositiveButton(getString(R.string.delete)) { _, _ -> listener?.onDialogPositiveClick() }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> listener?.onDialogNegativeClick() }

        return builder.create()
    }
}