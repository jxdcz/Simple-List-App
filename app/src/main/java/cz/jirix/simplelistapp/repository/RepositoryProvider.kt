package cz.jirix.simplelistapp.repository

class RepositoryProvider private constructor() {

    companion object {
        val INSTANCE = RepositoryProvider()
    }

    val user by lazy { UserRepository() }

}
