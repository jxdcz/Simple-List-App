package cz.jirix.simplelistapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import cz.jirix.simplelistapp.api.UsersApi
import cz.jirix.simplelistapp.model.User
import cz.jirix.simplelistapp.persistence.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserRepository(
    usersApi: UsersApi,
    appDatabase: AppDatabase
) {

    private val userApi = usersApi
    private val userDao = appDatabase.userDao()

    fun getUsers(): LiveData<List<User>> = userDao.getAll()

    fun removeUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.delete(user)
        }
    }

    suspend fun fetchUsersFromApi(): Boolean {
        return try {
            userApi.loadUsers().users.let {
                userDao.insertAll(it)
            }
            true
        } catch (e: IOException) {
            Log.e("UserRepository", e.toString())
            false
        } catch (e: HttpException) {
            Log.e("UserRepository", e.toString())
            false
        }
    }
}
