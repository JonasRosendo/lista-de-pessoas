package com.jonasrosendo.desafiousemobile.presentation.di

import com.jonasrosendo.desafiousemobile.presentation.viewmodels.ProfileViewModel
import com.jonasrosendo.desafiousemobile.presentation.viewmodels.UserViewModel
import dagger.Component

@Component(modules = [UseMobileModule::class])
interface UseMobileComponent {
    fun inject(viewModel: UserViewModel)
    fun inject(viewModel: ProfileViewModel)
}