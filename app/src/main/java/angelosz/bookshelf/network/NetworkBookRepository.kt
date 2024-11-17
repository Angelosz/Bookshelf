package angelosz.bookshelf.network

import angelosz.bookshelf.data.BookRepository
import angelosz.bookshelf.model.BookDetailData
import angelosz.bookshelf.model.BookThumbnailData

class NetworkBookRepository(
    private val apiService: BookshelfApiService
): BookRepository {
    private val booksDetails: MutableList<BookDetailData> = mutableListOf()

    override suspend fun getBooksThumbnails(): List<BookThumbnailData> {
        return apiService.getBooksThumbnail()
    }

    override suspend fun getBookDetail(id: String): BookDetailData {
        var book = booksDetails.find { book -> book.id == id }
        if( book != null) return book
        book = apiService.getBook(id)
        booksDetails.add(book)
        return book
    }
}