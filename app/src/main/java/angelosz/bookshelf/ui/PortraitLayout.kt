package angelosz.bookshelf.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import angelosz.bookshelf.R
import angelosz.bookshelf.ui.viewmodel.BookshelfUIState

@Composable
fun PortraitLayout(
    uiState: BookshelfUIState,
    modifier: Modifier = Modifier,
    onThumbnailClick: (String) -> Unit,
    onBackPressed: () -> Unit
){
    BackHandler {
        onBackPressed()
    }

    when(uiState){
        is BookshelfUIState.Success -> {
            val _uiState = uiState as BookshelfUIState.Success
            if(_uiState.isDetailView){
                _uiState.currentBook?.let {
                    BookDetailView(
                        book = it,
                        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
                        )
                } ?: Text(text = "No book found :(")
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    modifier = modifier
                ) {
                    items(uiState.booksThumbnails) {book ->
                        BookThumbnail(
                            book = book,
                            onThumbnailClick = {
                                onThumbnailClick(book.id)
                            }
                        )
                    }
                }
            }
        }
        is BookshelfUIState.BookshelfError -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = uiState.message ?: "There was an error :("
                )
            }
        }
        BookshelfUIState.BookshelfLoading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Loading..."
                )
            }
        }
    }
}