package angelosz.bookshelf.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import angelosz.bookshelf.R
import angelosz.bookshelf.ui.viewmodel.BookshelfUIState

@Composable
fun LandscapeLayout(
    uiState: BookshelfUIState,
    modifier: Modifier = Modifier,
    onThumbnailClick: (String) -> Unit
){
    when(uiState){
        is BookshelfUIState.Success -> {
            Surface(modifier = modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth()){
                    ThumbnailList(
                        uiState.booksThumbnails,
                        onThumbnailClick = onThumbnailClick,
                        modifier = Modifier.weight(0.6f)
                    )
                    uiState.currentBook?.let {
                        BookDetailView(
                            book = it,
                            modifier = modifier
                                .padding(dimensionResource(R.dimen.padding_medium))
                                .weight(0.4f)
                        )
                    } ?: Text(text = "No book selected.")
                }
            }
        }
        is BookshelfUIState.BookshelfError -> ErrorMessage(uiState.message ?: "There was an error :(")
        BookshelfUIState.BookshelfLoading -> LoadingMessage()
    }
}