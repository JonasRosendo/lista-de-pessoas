package com.jonasrosendo.desafiousemobile.presentation.di

import com.jonasrosendo.desafiousemobile.data.api.ApiService
import dagger.Component

@Component(modules = [UseMobileModule::class])
interface UseMobileComponent {
    fun inject(api: ApiService)
}