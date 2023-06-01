package com.merttoptas.shoppingcompose.core.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by mertcantoptas on 01.06.2023
 */
data class ProductResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: String?
)