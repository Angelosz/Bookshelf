package angelosz.bookshelf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import angelosz.bookshelf.R
import angelosz.bookshelf.model.BookDetailData
import angelosz.bookshelf.model.BookDetailImageLinks
import angelosz.bookshelf.model.BookDetailVolumeInfo
import angelosz.bookshelf.ui.theme.BookshelfTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BookDetailView(book: BookDetailData, modifier: Modifier = Modifier){
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ){
            AsyncImage(
                model = ImageRequest
                    .Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks.image.replace("http", "https"))
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_launcher_foreground),
                placeholder = painterResource(R.drawable.bookshelf_medium_image_example),
                contentDescription = book.volumeInfo.title,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text (
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )

            var authors = ""
            book.volumeInfo.authors.forEach { author ->
                authors += " $author "
            }
            Text (
                text = "Authors: $authors",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_small))
            )
            var categories = ""
            book.volumeInfo.categories.forEach { category ->
                categories += " $category "
            }
            Text (
                text = "Categories: $categories",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_small))
            )
            Text (
                text = "Page Count: ${book.volumeInfo.pageCount} pages",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_small))
            )
            Text (
                text = book.volumeInfo.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_small))
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookDetailViewPreview(){
    BookshelfTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            BookDetailView(
                BookDetailData(
                    id = "1zpbEUd74fIC",
                    volumeInfo = BookDetailVolumeInfo(
                        BookDetailImageLinks(
                            image = ""
                        ),
                        title = "Historia del jazz",
                        authors = listOf("Ted Gioia"),
                        categories = listOf("Music / Genres & Styles / Jazz"),
                        pageCount = 608,
                        description = "Músico e historiador, Ted Gioia une en Historia del jazz los conocimientos del erudito con las virtudes expresivas del divulgador entusiasta para componer el más completo panorama de este género: desde su prehistoria y la temprana africanización de la música americana, hasta las semblanzas de las grandes figuras del siglo XX y de los distintos estilos y geografías. Una autorizada obra de investigación que, sin embargo, no escatima el placer de las palabras"
                    )
                )
            )
        }
    }
}