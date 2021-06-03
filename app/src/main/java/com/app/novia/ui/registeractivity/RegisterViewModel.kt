package com.app.novia.ui.registeractivity

import androidx.lifecycle.*
import com.app.novia.core.domain.model.UserModel
import com.app.novia.core.domain.usecase.NoviaUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.gson.JsonObject

class RegisterViewModel constructor(private val useCase: NoviaUseCase) : ViewModel() {

    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val _registerResult = MutableLiveData<Pair<Boolean, String?>>()

    suspend fun addUser(user: JsonObject?) = useCase.addUser(user).asLiveData()

    fun registerUser(
        userModel: UserModel
    ) {
        auth.createUserWithEmailAndPassword(userModel.email, userModel.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(userModel.name)
//                        .setPhotoUri()
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { it1 ->
                        if (it1.isSuccessful) {
                            _registerResult.postValue(
                                Pair(
                                    true,
                                    "Halo, selamat datang " + user.displayName + "!"
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