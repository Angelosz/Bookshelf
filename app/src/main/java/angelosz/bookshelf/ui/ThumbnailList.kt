package angelosz.bookshelf.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import angelosz.bookshelf.model.BookThumbnailData

@Composable
fun ThumbnailList(
    thumbnails: List<BookThumbnailData>,
    onThumbnailClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
    ) {
        items(thumbnails) { book ->
            BookThumbnail(
                book = book,
                onThumbnailClick = {
                    onThumbnailClick(book.id)
                }
            )
        }
    }
}