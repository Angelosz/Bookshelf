package angelosz.bookshelf.data

import angelosz.bookshelf.network.BookshelfApiService
import angelosz.bookshelf.network.NetworkBookRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val repository: BookRepository
}

class DefaultAppContainer : AppContainer{
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    private val json = Json{ ignoreUnknownKeys = true }
    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val apiService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }

    override val repository: BookRepository by lazy {
        NetworkBookRepository(apiService)
    }
}