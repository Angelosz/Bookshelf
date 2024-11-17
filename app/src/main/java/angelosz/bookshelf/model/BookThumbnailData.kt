package angelosz.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookThumbnails(
    val items: List<BookThumbnailData>
)

@Serializable
data class BookThumbnailData(
    val id: String,
    val volumeInfo: BookThumbnailVolumeInfo
)

@Serializable
data class BookThumbnailVolumeInfo(
    val title: String,
    val imageLinks: BookThumbnailImageLinks
)

@Serializable
data class BookThumbnailImageLinks(
    val thumbnail: String
)