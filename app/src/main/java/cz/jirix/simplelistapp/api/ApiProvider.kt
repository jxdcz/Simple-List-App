package cz.jirix.simplelistapp.api

import android.content.Context
import cz.jirix.simplelistapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiProvider private constructor() {

    companion object {
        val INSTANCE = ApiProvider()
    }

    lateinit var usersApi: UsersApi


    fun init(context: Context) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.USERS_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        usersApi = retrofit.create(UsersApi::class.java)
    }

}
