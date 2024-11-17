package angelosz.bookshelf.ui.viewmodel

import angelosz.bookshelf.model.BookDetailData
import angelosz.bookshelf.model.BookThumbnailData

sealed interface BookshelfUIState {
    data class Success(
        val booksThumbnails: List<BookThumbnailData>,
        val books: MutableList<BookDetailData>,
        var currentBook: BookDetailData?,
        var isDetailView: Boolean
    ): BookshelfUIState
    data class BookshelfError(
        val message: String?
    ): BookshelfUIState
    data object BookshelfLoading: BookshelfUIState
}