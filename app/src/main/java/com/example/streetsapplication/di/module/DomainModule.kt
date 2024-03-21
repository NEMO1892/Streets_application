package com.example.streetsapplication.di.module

import com.example.streetsapplication.data.repository.StreetsRepository
import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun provideStreetsRepository(repository: StreetsRepository): StreetsRepositoryContract
}