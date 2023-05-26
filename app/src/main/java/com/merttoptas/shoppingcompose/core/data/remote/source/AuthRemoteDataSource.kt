package com.merttoptas.shoppingcompose.core.data.remote.source

import com.merttoptas.shoppingcompose.core.data.model.LoginResponse

/**
 * Created by mertcantoptas on 26.05.2023
 */

interface AuthRemoteDataSource {
    suspend fun login(username: String, password: String): Result<LoginResponse>
}