package com.jonasrosendo.desafiousemobile.di

import com.jonasrosendo.desafiousemobile.data.api.UseMobileApi
import com.jonasrosendo.desafiousemobile.data.api.UseMobileService
import dagger.Component

@Component(modules = [UseMobileModule::class])
interface UseMobileComponent {
    fun inject(api: UseMobileService)
}