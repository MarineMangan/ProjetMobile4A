package com.example.projetmobile4a.domain.usecase

import com.example.projetmobile4a.data.repository.UserRepository
import com.example.projetmobile4a.domain.entity.User

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(email: String, password: String) :User? {
        return userRepository.getUser(email, password)
    }
}