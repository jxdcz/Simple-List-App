package cz.jirix.simplelistapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.jirix.simplelistapp.api.ApiProvider
import cz.jirix.simplelistapp.model.Progress
import cz.jirix.simplelistapp.model.User
import cz.jirix.simplelistapp.persistence.DbProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UserRepository {

    private val userApi = ApiProvider.INSTANCE.usersApi
    private val userDao = DbProvider.INSTANCE.userDao()

    fun getUsers(): LiveData<List<User>> = userDao.getAll()

    fun removeUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.delete(user)
        }
    }

    suspend fun fetchUsersFromApi(): LiveData<Progress> {
        val liveData = MutableLiveData<Progress>()
        try {
            withContext(Dispatchers.Main) { liveData.value = Progress.loading() }
            userApi.loadUsers().users.let {
                userDao.insertAll(it)
            }
            withContext(Dispatchers.Main) { liveData.value = Progress.success() }
        } catch (e: IOException) {
            withContext(Dispatchers.Main) { liveData.value = Progress.error() }
        } catch (e: HttpException) {
            withContext(Dispatchers.Main) { liveData.value = Progress.error() }

        }
        return liveData
    }
}
