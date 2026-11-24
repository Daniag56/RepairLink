package com.revup.features.auth.di

import com.revup.features.auth.data.remote.AuthDatasource
import com.revup.features.auth.data.remote.RepairLinkDatasource
import com.revup.features.auth.data.repository.AuthRepository
import com.revup.features.auth.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
interface AuthModule {

    @Binds
    @Singleton
    fun bindsAuthRepository(r: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsAuthRemoteDatasource(ds: RepairLinkDatasource): AuthDatasource
}