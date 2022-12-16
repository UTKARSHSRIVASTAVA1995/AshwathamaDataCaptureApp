package com.utkarsh.ashwathama.di

import AshwathamaService
import android.content.Context
import com.utkarsh.ashwathama.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext appContext: Context
    ): Retrofit = AshwathamaService.createRetrofit(appContext)

    @Provides
    @Singleton
    fun provideNetworkConnection(
        @ApplicationContext appContext: Context
    ): NetworkConnectionInterceptor = NetworkConnectionInterceptor(appContext)


}