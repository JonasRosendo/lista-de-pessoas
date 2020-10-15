package com.jonasrosendo.desafiousemobile.di

import com.jonasrosendo.desafiousemobile.data.api.Api
import com.jonasrosendo.desafiousemobile.data.api.ApiService
import com.jonasrosendo.desafiousemobile.data.datasource.UserDataSource
import com.jonasrosendo.desafiousemobile.data.repository.UserRepository
import com.jonasrosendo.desafiousemobile.usecases.InteractorGetUsers
import com.jonasrosendo.desafiousemobile.usecases.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseMobileModule {

    @Provides
    fun provideApi(): Api = ApiService

    @Provides
    fun provideUserDataSource(api: Api) = UserDataSource(api)

    @Provides
    fun provideUserRepository(dataSource: UserDataSource) = UserRepository(dataSource)

    @Provides
    fun provideUseCases(repository: UserRepository) = UseCases(InteractorGetUsers(repository))
}