package com.jonasrosendo.desafiousemobile.di

import com.jonasrosendo.desafiousemobile.data.api.UseMobileApi
import com.jonasrosendo.desafiousemobile.data.api.UseMobileService
import com.jonasrosendo.desafiousemobile.data.datasource.UserDataSource
import com.jonasrosendo.desafiousemobile.data.repository.UserRepository
import com.jonasrosendo.desafiousemobile.usecases.InteractorGetUsers
import com.jonasrosendo.desafiousemobile.usecases.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseMobileModule {

    @Provides
    fun provideApi(): UseMobileApi = UseMobileService

    @Provides
    fun provideUserDataSource(api: UseMobileApi) = UserDataSource(api)

    @Provides
    fun provideUserRepository(dataSource: UserDataSource) = UserRepository(dataSource)

    @Provides
    fun provideUseCases(repository: UserRepository) = UseCases(InteractorGetUsers(repository))
}