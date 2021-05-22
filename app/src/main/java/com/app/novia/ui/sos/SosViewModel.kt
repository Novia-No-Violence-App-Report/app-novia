package com.app.novia.ui.sos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SosViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is SOS Fragment"
    }
    val text: LiveData<String> = _text
}