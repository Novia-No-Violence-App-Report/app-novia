package com.app.novia.ui.home

import androidx.lifecycle.ViewModel
import com.app.novia.core.domain.usecase.NoviaUseCase

class HomeViewModel constructor(private val useCase: NoviaUseCase) : ViewModel()