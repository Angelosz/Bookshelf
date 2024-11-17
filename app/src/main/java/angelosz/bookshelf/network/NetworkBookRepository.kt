package angelosz.bookshelf.network

import angelosz.bookshelf.data.BookRepository
import angelosz.bookshelf.model.BookDetailData
import angelosz.bookshelf.model.BookThumbnailData

class NetworkBookRepository(
    private val apiService: BookshelfApiService
): BookRepository {

    override suspend fun getBooksThumbnails(): List<BookThumbnailData> {
        return apiService.getBooksThumbnail().items
    }

    override suspend fun getBookDetail(id: String): BookDetailData {
         return apiService.getBook(id)
    }
}