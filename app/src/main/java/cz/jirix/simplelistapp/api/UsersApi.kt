package cz.jirix.simplelistapp.api

import retrofit2.http.GET

interface UsersApi {
    @GET("/users.json")
    suspend fun loadUsers(): GetUsersResponse

}
