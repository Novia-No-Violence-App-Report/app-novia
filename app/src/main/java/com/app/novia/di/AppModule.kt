package com.app.novia.di

import com.app.novia.core.domain.usecase.NoviaInteractor
import com.app.novia.core.domain.usecase.NoviaUseCase
import com.app.novia.ui.contactlist.ContactListViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NoviaUseCase> { NoviaInteractor(get()) }
}

@DelicateCoroutinesApi
val viewModelModule = module {
    viewModel { ContactListViewModel(get()) }
}