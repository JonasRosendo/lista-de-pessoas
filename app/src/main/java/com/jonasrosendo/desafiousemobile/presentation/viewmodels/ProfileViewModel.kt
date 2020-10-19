package com.jonasrosendo.desafiousemobile.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.domain.usecases.UseCases
import com.jonasrosendo.desafiousemobile.presentation.commons.NetworkUtils
import com.jonasrosendo.desafiousemobile.presentation.di.DaggerUseMobileComponent
import kotlinx.coroutines.*
import javax.inject.Inject

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.stackTrace
        hasError.value = true
    }

    val user = MutableLiveData<User>()
    val hasError = MutableLiveData(false)
    val isProfileLoading = MutableLiveData(false)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerUseMobileComponent.builder().build().inject(this)
    }

    fun getUserDetails(id: Long) {
        isProfileLoading.postValue(true)
        getUserById(id)
    }

    private fun getUserById(id: Long) {
        job = coroutineScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = useCases.getUserByIdUserCase.invoke(id)
            withContext(Dispatchers.Main) {
                user.value = response
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        job?.let { if (it.isCancelled) job = null }
        super.onCleared()
    }
}