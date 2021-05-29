package com.app.novia.di

import com.app.novia.core.domain.usecase.NoviaInteractor
import com.app.novia.core.domain.usecase.NoviaUseCase
import com.app.novia.ui.chatbot.ChatViewModel
import com.app.novia.ui.contactlist.ContactListViewModel
import com.app.novia.ui.home.HomeViewModel
import com.app.novia.ui.mainactivity.MainViewModel
import com.app.novia.ui.settingsactivity.SettingsViewModel
import com.app.novia.ui.sos.SosViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NoviaUseCase> { NoviaInteractor(get()) }
}

@DelicateCoroutinesApi
val viewModelModule = module {
    viewModel { ContactListViewModel(get()) }
    viewModel { SosViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { ChatViewModel(get()) }
}