package angelosz.bookshelf.data

import angelosz.bookshelf.model.BookDetailData
import angelosz.bookshelf.model.BookThumbnailData

interface BookRepository {
    suspend fun getBooksThumbnails(): List<BookThumbnailData>
    suspend fun getBookDetail(id: String): BookDetailData
}