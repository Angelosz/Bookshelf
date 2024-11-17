package angelosz.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetailData(
    val id: String = "",
    val volumeInfo: BookDetailVolumeInfo = BookDetailVolumeInfo()
)

@Serializable
data class BookDetailVolumeInfo (
    val imageLinks: BookDetailImageLinks = BookDetailImageLinks(""),
    val title: String = "Book has no Title",
    val authors: List<String> = listOf(),
    val categories: List<String> = listOf(),
    val pageCount: Int = 0,
    val description: String = "Book has no description"
)

@Serializable
data class BookDetailImageLinks (
    @SerialName(value = "small")
    val image: String = ""
)
