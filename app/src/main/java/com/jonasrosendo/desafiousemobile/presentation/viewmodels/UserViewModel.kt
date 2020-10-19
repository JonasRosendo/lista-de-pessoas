package com.jonasrosendo.desafiousemobile.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.domain.entities.UserResponse
import com.jonasrosendo.desafiousemobile.domain.usecases.UseCases
import com.jonasrosendo.desafiousemobile.presentation.commons.NetworkUtils
import com.jonasrosendo.desafiousemobile.presentation.db.UserDatabase
import com.jonasrosendo.desafiousemobile.presentation.db.mappers.RoomRepository
import com.jonasrosendo.desafiousemobile.presentation.db.mappers.UserMapper
import com.jonasrosendo.desafiousemobile.presentation.di.DaggerUseMobileComponent
import kotlinx.coroutines.*
import javax.inject.Inject

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = UserDatabase.getDatabase(application).userDao()
    private val repository = RoomRepository(userDao)

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.stackTrace
        hasError.value = true
    }

    val users = MutableLiveData<UserResponse>()
    val user = MutableLiveData<User?>()
    val isUsersLoading = MutableLiveData(false)

    val hasError = MutableLiveData(false)
    val isDeviceConnected = MutableLiveData(false)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerUseMobileComponent.builder().build().inject(this)
    }

    fun getAllUsers() {
        isUsersLoading.value = true
        isDeviceConnected.value = NetworkUtils.checkInternetConnection(getApplication())
        isDeviceConnected.value?.let { hasInternetConnection ->
            if (hasInternetConnection) getUsers() else isUsersLoading.postValue(false)
        }
    }

    private fun insertUsersOnLocalDatabase(users: List<User>) {
        coroutineScope.launch(Dispatchers.IO + exceptionHandler) {
            repository.insertUsers(users)
        }
    }

    fun getAllUsersOnLocalDatabase() {
        coroutineScope.launch(Dispatchers.IO) {
            users.postValue(UserMapper.toUsers(repository.getAllUsersOnLocalDatabase()))
        }
    }

    private fun getUsers() {
        job = coroutineScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = useCases.getUsers.invoke()
            withContext(Dispatchers.Main) {
                users.value = response
            }
            users.value?.response?.let { insertUsersOnLocalDatabase(it) }
            isUsersLoading.postValue(false)
        }
    }

    fun getAllUsers(name: String?) {
        coroutineScope.launch(Dispatchers.IO) {
            name?.let {
                val list = UserMapper.toUsers(repository.searchUsersByName(name))
                withContext(Dispatchers.Main) {
                    users.value = list
                }
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        job?.let { if (it.isCancelled) job = null }
        coroutineScope.launch { userDao.clearUserTable() }
        super.onCleared()
    }
}