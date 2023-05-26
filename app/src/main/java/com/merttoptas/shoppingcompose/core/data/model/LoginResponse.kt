package com.merttoptas.shoppingcompose.core.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by mertcantoptas on 26.05.2023
 */

data class LoginResponse(
    @SerializedName("token")
    val token: String?
)