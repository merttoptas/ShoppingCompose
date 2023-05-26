package com.merttoptas.shoppingcompose.core.data.remote.api

import com.merttoptas.shoppingcompose.core.data.model.LoginBody
import com.merttoptas.shoppingcompose.core.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by mertcantoptas on 26.05.2023
 */

interface AuthService {

   @POST("auth/login")
   suspend fun login(
       @Body requestBody: LoginBody,
   ) : LoginResponse
}