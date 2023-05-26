package com.merttoptas.shoppingcompose.core.data.remote.repository.impl

import com.merttoptas.shoppingcompose.core.data.model.LoginResponse
import com.merttoptas.shoppingcompose.core.data.remote.repository.AuthRepository
import com.merttoptas.shoppingcompose.core.data.remote.source.AuthRemoteDataSource
import javax.inject.Inject

/**
 * Created by mertcantoptas on 26.05.2023
 */

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return  authRemoteDataSource.login(username, password)
    }
}