package cz.jirix.simplelistapp.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import cz.jirix.simplelistapp.model.Progress
import cz.jirix.simplelistapp.model.User
import cz.jirix.simplelistapp.repository.RepositoryProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserListViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = RepositoryProvider.INSTANCE.user
    private var runningJob: Job? = null
    private val loadingProgress = MutableLiveData<Progress>()

    fun getUsers(): LiveData<List<User>> = userRepository.getUsers()
    fun getLoadingProgress(): LiveData<Progress> = loadingProgress

    fun removeUser(user: User) {
        userRepository.removeUser(user)
    }

    fun refreshUsersFromApi() {
        runningJob = CoroutineScope(Dispatchers.IO).launch {
            userRepository.fetchUsersFromApi().map { loadingProgress.value = it }
        }
    }

    fun cancelRefresh() {
        if (runningJob?.isActive == true) {
            runningJob?.cancel()
        }
    }
}
