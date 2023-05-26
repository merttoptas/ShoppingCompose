package com.merttoptas.shoppingcompose.core.data.remote.repository

import com.merttoptas.shoppingcompose.core.data.model.LoginResponse

/**
 * Created by mertcantoptas on 26.05.2023
 */

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<LoginResponse>
}