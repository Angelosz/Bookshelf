package angelosz.bookshelf

import android.app.Application
import angelosz.bookshelf.data.AppContainer
import angelosz.bookshelf.data.DefaultAppContainer

class BookshelfApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}