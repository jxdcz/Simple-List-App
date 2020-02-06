package cz.jirix.simplelistapp

import android.app.Application
import cz.jirix.simplelistapp.api.ApiProvider
import cz.jirix.simplelistapp.persistence.DbProvider

class UserListApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ApiProvider.INSTANCE.init(this)
        DbProvider.init(this)
    }
}
