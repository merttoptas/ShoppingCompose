package com.merttoptas.shoppingcompose.domain.usecase.prduct

import com.merttoptas.shoppingcompose.core.data.model.ProductResponse
import com.merttoptas.shoppingcompose.core.data.remote.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by mertcantoptas on 01.06.2023
 */
class GetProductDetailUseCase @Inject constructor(private val productRepository: ProductRepository) {
    operator fun invoke(id: Int): Flow<ProductResponse> {
        return flow {
            val result = productRepository.getProductDetail(id)
            (result.getOrNull() ?: throw IllegalArgumentException("error message")).also {
                emit(it)
            }
        }
    }
}