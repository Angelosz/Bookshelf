package angelosz.bookshelf.network

import angelosz.bookshelf.model.BookDetailData
import angelosz.bookshelf.model.BookThumbnails
import retrofit2.http.GET
import retrofit2.http.Path

interface BookshelfApiService {
    @GET("volumes?q=jazz+history")
    suspend fun getBooksThumbnail(): BookThumbnails
    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): BookDetailData
}