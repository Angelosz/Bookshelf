package angelosz.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetailData(
    val id: String,
    val title: String,
    val volumeInfo: BookDetailVolumeInfo
)

@Serializable
data class BookDetailVolumeInfo (
    val imageLinks: BookDetailImageLinks,
    val authors: List<String>,
    val categories: List<String>,
    val pageCount: Int,
    val description: String
)

@Serializable
data class BookDetailImageLinks (
    @SerialName(value = "small")
    val image: String
)
