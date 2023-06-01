package com.merttoptas.shoppingcompose.core.data.remote.api

import com.merttoptas.shoppingcompose.core.data.model.ProductResponse
import retrofit2.http.GET

/**
 * Created by mertcantoptas on 01.06.2023
 */
interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductDetail(id: Int): ProductResponse
}