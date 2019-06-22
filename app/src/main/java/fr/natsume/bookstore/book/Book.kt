package fr.natsume.bookstore.book

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [
    Index(name = "ux_book_title", value = ["title"], unique = true)
])
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Long
    , var title: String
    , var author: String
    , var summary: String
    , @ColumnInfo(name = "picture_url")
    var pictureUrl: String
)
