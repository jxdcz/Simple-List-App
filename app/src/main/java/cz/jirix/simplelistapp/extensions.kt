package cz.jirix.simplelistapp

import android.content.Context
import cz.jirix.simplelistapp.repository.RepositoryProvider

fun Context.getRepositoryProvider(): RepositoryProvider {
    return (this as UserListApp).repositoryProvider
}
