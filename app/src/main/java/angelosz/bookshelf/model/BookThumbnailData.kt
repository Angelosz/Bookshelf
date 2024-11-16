package angelosz.bookshelf.model

import kotlinx.serialization.Serializable


@Serializable
data class BookThumbnailData(
    val id: String,
    val title: String,
    val volumeInfo: BookThumbnailVolumeInfo
)

@Serializable
data class BookThumbnailVolumeInfo(
    val imageLinks: BookThumbnailImageLinks
)

@Serializable
data class BookThumbnailImageLinks(
    val thumbnail: String
)