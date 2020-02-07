package cz.jirix.simplelistapp.repository

import cz.jirix.simplelistapp.api.ApiProvider
import cz.jirix.simplelistapp.persistence.AppDatabase

class RepositoryProvider(
    private val apiProvider: ApiProvider,
    private val appDatabase: AppDatabase
) {

    val user by lazy { UserRepository(apiProvider.usersApi, appDatabase) }

}
