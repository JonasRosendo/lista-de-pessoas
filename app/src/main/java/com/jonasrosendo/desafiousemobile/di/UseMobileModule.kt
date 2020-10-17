package com.jonasrosendo.desafiousemobile.di

import com.jonasrosendo.desafiousemobile.data.api.Api
import com.jonasrosendo.desafiousemobile.data.api.ApiService
import com.jonasrosendo.desafiousemobile.data.datasource.UserDataSource
import com.jonasrosendo.desafiousemobile.data.repositories.UserRepositoryImpl
import com.jonasrosendo.desafiousemobile.domain.repositories.UserRepository
import com.jonasrosendo.desafiousemobile.domain.usecases.GetUserByIdUserCase
import com.jonasrosendo.desafiousemobile.domain.usecases.GetUsersUseCase
import com.jonasrosendo.desafiousemobile.domain.usecases.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseMobileModule {

    @Provides
    fun provideApi(): Api = ApiService

    @Provides
    fun provideUserDataSource(api: Api) = UserDataSource(api)

    @Provides
    fun provideUserRepository(dataSource: UserDataSource) = UserRepositoryImpl(dataSource)

    @Provides
    fun provideUseCases(repository: UserRepository) = UseCases(
        GetUsersUseCase(repository),
        GetUserByIdUserCase(repository)
    )
}