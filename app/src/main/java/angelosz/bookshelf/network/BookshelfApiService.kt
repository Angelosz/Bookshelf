package angelosz.bookshelf.network

import angelosz.bookshelf.model.BookDetailData
import angelosz.bookshelf.model.BookThumbnailData
import retrofit2.http.GET
import retrofit2.http.Path

interface BookshelfApiService {
    @GET("?q=jazz+history")
    suspend fun getBooksThumbnail(): List<BookThumbnailData>
    @GET("/{id}")
    suspend fun getBook(@Path("id") id: String): BookDetailData
}