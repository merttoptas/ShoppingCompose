package com.merttoptas.shoppingcompose.core.data.di

import com.merttoptas.shoppingcompose.core.data.remote.repository.ProductRepository
import com.merttoptas.shoppingcompose.core.data.remote.repository.impl.ProductRepositoryImpl
import com.merttoptas.shoppingcompose.core.data.remote.source.ProductRemoteDataImpl
import com.merttoptas.shoppingcompose.core.data.remote.source.ProductRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by mertcantoptas on 01.06.2023
 */

@Module
@InstallIn(ViewModelComponent::class)
interface ProductModule {
    @Binds
    fun bindProductRemoteDataSource(
        sourceImpl: ProductRemoteDataImpl
    ): ProductRemoteDataSource

    @Binds
    fun bindProductRepository(
        sourceImpl: ProductRepositoryImpl
    ): ProductRepository

}