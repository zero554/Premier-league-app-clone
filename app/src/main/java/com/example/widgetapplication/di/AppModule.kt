package com.example.widgetapplication.di

import com.example.widgetapplication.data.remote.FootballApi
import com.example.widgetapplication.domain.repository.FootballRepository
import com.example.widgetapplication.usecase.GetFootballEventsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFootballApi(): FootballApi {
        return Retrofit
            .Builder()
            .baseUrl(FootballApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGetFootballUseCase(
        footballRepository: FootballRepository
    ): GetFootballEventsUseCase {
        return GetFootballEventsUseCase(footballRepository)
    }

}