package com.merttoptas.shoppingcompose.domain.usecase.login

import com.merttoptas.shoppingcompose.core.data.model.LoginResponse
import com.merttoptas.shoppingcompose.core.data.remote.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by mertcantoptas on 26.05.2023
 */

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(username: String, password: String): Flow<LoginResponse> {
        return flow {
            val result = authRepository.login(username, password)
            (result.getOrNull() ?: throw IllegalArgumentException("error message")).also {
                emit(it)
            }
        }
    }
}