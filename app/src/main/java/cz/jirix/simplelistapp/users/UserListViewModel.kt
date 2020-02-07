package cz.jirix.simplelistapp.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.jirix.simplelistapp.getRepositoryProvider
import cz.jirix.simplelistapp.model.Progress
import cz.jirix.simplelistapp.model.User
import kotlinx.coroutines.*

class UserListViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = application.getRepositoryProvider().user
    private var runningJob: Job? = null
    private val loadingProgress = MutableLiveData<Progress>()

    fun getUsers(): LiveData<List<User>> = userRepository.getUsers()
    fun getLoadingProgress(): LiveData<Progress> = loadingProgress

    fun removeUser(user: User) {
        userRepository.removeUser(user)
    }

    fun refreshUsersFromApi() {
        runningJob = CoroutineScope(Dispatchers.Main).launch {
            loadingProgress.value = Progress.loading()
            val success = withContext(Dispatchers.IO) { userRepository.fetchUsersFromApi() }
            loadingProgress.value = if (success) Progress.success() else Progress.error()
        }
    }

    fun cancelRefresh() {
        if (runningJob?.isActive == true) {
            runningJob?.cancel()
        }
    }

    fun resetProgress() {
        if (loadingProgress.value?.state != Progress.IDLE) {
            loadingProgress.value = Progress.idle()
        }
    }
}
