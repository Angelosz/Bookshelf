package angelosz.bookshelf

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import angelosz.bookshelf.ui.PortraitLayout
import angelosz.bookshelf.ui.viewmodel.BookshelfUIState
import angelosz.bookshelf.ui.viewmodel.BookshelfViewModel

@Composable
fun AppLayout(
    windowSize: WindowWidthSizeClass
){
    val viewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
    Scaffold(
        topBar = {
            if( viewModel.uiState is BookshelfUIState.Success ){
                BookShelfTopAppBar(
                    showBackButton = (viewModel.uiState as BookshelfUIState.Success).isDetailView && windowSize != WindowWidthSizeClass.Expanded,
                    onBackButtonClick = {
                        viewModel.navigateToListPage()
                    }
                )
            }
        }
    ) { innerPadding ->
        PortraitLayout(
            viewModel.uiState,
            modifier = Modifier.padding(innerPadding),
            onThumbnailClick = { id -> viewModel.getBook(id) },
            onBackPressed = {
                viewModel.navigateToListPage()
            }
        )
    }
}

@Composable
fun BookShelfTopAppBar(showBackButton: Boolean = false, onBackButtonClick: () -> Unit){
    TopAppBar (
        title = {
            Text(
                text = "Bookshelf",
            )
        },
        navigationIcon = {
            if(showBackButton){
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            } else {
                Box {}
            }
        },
    )
}