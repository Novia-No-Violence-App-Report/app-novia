package com.app.novia.di

import com.app.novia.core.domain.usecase.NoviaInteractor
import com.app.novia.core.domain.usecase.NoviaUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NoviaUseCase> { NoviaInteractor(get()) }
}

@DelicateCoroutinesApi
val viewModelModule = module {
    // TODO: Add viewmodel to module
}