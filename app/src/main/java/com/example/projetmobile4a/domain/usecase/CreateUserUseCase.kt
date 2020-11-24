package com.example.projetmobile4a.domain.usecase

import com.example.projetmobile4a.data.repository.UserRepository
import com.example.projetmobile4a.domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(user: User){
        userRepository.createUser(user)
    }
}