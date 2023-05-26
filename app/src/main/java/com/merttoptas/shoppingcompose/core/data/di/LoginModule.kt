package com.merttoptas.shoppingcompose.core.data.di

import com.merttoptas.shoppingcompose.core.data.remote.repository.AuthRepository
import com.merttoptas.shoppingcompose.core.data.remote.repository.impl.AuthRepositoryImpl
import com.merttoptas.shoppingcompose.core.data.remote.source.AuthRemoteDataSource
import com.merttoptas.shoppingcompose.core.data.remote.source.AuthRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by mertcantoptas on 26.05.2023
 */

@Module
@InstallIn(ViewModelComponent::class)
interface LoginModule {
    @Binds
    fun bindLoginRemoteDataSource(
        sourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    fun bindLoginRepository(
        repositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}