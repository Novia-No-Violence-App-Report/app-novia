package com.app.novia.ui.registeractivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val _registerResult = MutableLiveData<Pair<Boolean, String?>>()

    fun registerUser(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
//                        .setPhotoUri()
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { it1 ->
                        if (it1.isSuccessful) {
                            _registerResult.postValue(
                                Pair(
                                    true,
                                    "Halo, selamat datang " + user.displayName
                                )
                            )
                        } else {
                            _registerResult.postValue(Pair(false, it1.exception?.message))
                        }

                    }
                } else {
                    _registerResult.postValue(Pair(false, it.exception?.message))
                }
            }
    }

    fun registerResult(): LiveData<Pair<Boolean, String?>> = _registerResult
}