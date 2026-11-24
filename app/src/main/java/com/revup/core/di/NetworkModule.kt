package com.revup.core.di

import com.revup.core.remote.RepairLinkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:3000/")
            .build()
    }


    @Provides
    @Singleton
    fun provideRepairLinkApi(r: Retrofit): RepairLinkApi {
        return r.create<RepairLinkApi>()
    }
}