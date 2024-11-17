package angelosz.bookshelf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import angelosz.bookshelf.R
import angelosz.bookshelf.model.BookThumbnailData
import angelosz.bookshelf.model.BookThumbnailImageLinks
import angelosz.bookshelf.model.BookThumbnailVolumeInfo
import angelosz.bookshelf.ui.theme.BookshelfTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BookThumbnail(
    book: BookThumbnailData,
    onThumbnailClick: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RectangleShape,
        onClick = {
            onThumbnailClick(book.id)
        }
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks.thumbnail)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_launcher_foreground),
                placeholder = painterResource(R.drawable.bookshelf_small_image_example),
                contentDescription = book.title,
            )
            Text(
                text = book.title,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@Preview
@Composable
fun BookThumbnailPreview(){
    BookshelfTheme {
            BookThumbnail(
                BookThumbnailData(
                    id = "id",
                    title = "Book Title",
                    volumeInfo = BookThumbnailVolumeInfo(
                        BookThumbnailImageLinks(
                            thumbnail = ""
                        )
                    )
                ),
                onThumbnailClick = {},
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
}