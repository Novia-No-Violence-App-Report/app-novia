package com.app.novia.ui.mainactivity

import androidx.lifecycle.ViewModel
import com.app.novia.core.domain.usecase.NoviaUseCase

class MainViewModel constructor(private val useCase: NoviaUseCase): ViewModel()