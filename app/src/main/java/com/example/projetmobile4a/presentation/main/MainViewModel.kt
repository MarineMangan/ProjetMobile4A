package com.example.projetmobile4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetmobile4a.domain.entity.User
import com.example.projetmobile4a.domain.usecase.CreateUserUseCase
import com.example.projetmobile4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext as withContext

class MainViewModel (
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel(){

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()


    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch (Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser, password)
            val loginStatus = if(user != null) {
                LoginSuccess(user.email, password)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main) {
                loginLiveData.value = loginStatus
            }
        }
    }
    fun onClickedLoginAccount(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(user)
        }
    }
}