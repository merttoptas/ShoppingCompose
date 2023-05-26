package com.merttoptas.shoppingcompose.core.data.remote.source

import com.merttoptas.shoppingcompose.core.data.model.LoginBody
import com.merttoptas.shoppingcompose.core.data.model.LoginResponse
import com.merttoptas.shoppingcompose.core.data.remote.api.AuthService
import javax.inject.Inject

/**
 * Created by mertcantoptas on 26.05.2023
 */

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return runCatching {
            authService.login(LoginBody(username, password))
        }
    }
}