package com.example.widgetapplication.di

import com.example.widgetapplication.data.remote.repository.FootballRepositoryImpl
import com.example.widgetapplication.domain.repository.FootballRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsFootballRepository(
        footballRepositoryImpl: FootballRepositoryImpl
    ): FootballRepository
}