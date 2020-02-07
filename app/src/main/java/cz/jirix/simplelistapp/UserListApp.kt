package cz.jirix.simplelistapp

import android.app.Application
import cz.jirix.simplelistapp.api.ApiProvider
import cz.jirix.simplelistapp.persistence.DbProvider
import cz.jirix.simplelistapp.repository.RepositoryProvider

class UserListApp : Application() {

    lateinit var repositoryProvider: RepositoryProvider

    override fun onCreate() {
        super.onCreate()

        ApiProvider.INSTANCE.init(this)
        DbProvider.init(this)

        repositoryProvider = RepositoryProvider(ApiProvider.INSTANCE, DbProvider.INSTANCE)
    }
}
