package angelosz.bookshelf.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import angelosz.bookshelf.BookshelfApplication
import angelosz.bookshelf.data.BookRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BookshelfViewModel(
    private val repository: BookRepository
): ViewModel() {
    var uiState: BookshelfUIState by mutableStateOf(BookshelfUIState.BookshelfLoading)
        private set

    init {
        loadBookThumbnails()
    }

    private fun loadBookThumbnails() {
        viewModelScope.launch {
            try {
                uiState = BookshelfUIState.Success(
                    booksThumbnails = repository.getBooksThumbnails(),
                    books = mutableListOf(),
                    currentBook = null,
                    false
                )
            } catch(e: IOException){
                uiState = BookshelfUIState.BookshelfError(e.message)
            } catch(e: HttpException){
                uiState = BookshelfUIState.BookshelfError(e.message)
            } catch(e: Exception){
                uiState = BookshelfUIState.BookshelfError(e.message)
            }
        }
    }

    fun getBook(id: String) {
        when(uiState){
            is BookshelfUIState.Success -> {
                val uiStateAsSuccess = uiState as BookshelfUIState.Success
                val book = uiStateAsSuccess.books.find { it.id == id}
                if(book != null){
                    uiState = uiStateAsSuccess.copy(currentBook = book)
                }
                else {
                    viewModelScope.launch {
                        try {
                            val bookFound = repository.getBookDetail(id)
                            uiStateAsSuccess.books.add(bookFound)
                            uiState = uiStateAsSuccess.copy(currentBook = bookFound, isDetailView = true)
                        } catch(e: Exception){
                            uiState = uiStateAsSuccess.copy(currentBook = null)
                        }
                    }
                }
            }
            is BookshelfUIState.BookshelfError -> return
            BookshelfUIState.BookshelfLoading -> return
        }
    }

    fun navigateToListPage(){
        uiState = (uiState as BookshelfUIState.Success).copy(isDetailView = false)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val repository = application.container.repository
                BookshelfViewModel(repository = repository)
            }
        }
    }
}