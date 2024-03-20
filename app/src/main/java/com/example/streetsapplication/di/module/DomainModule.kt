package com.example.streetsapplication.di.module

import com.example.streetsapplication.data.repository.StreetsRepository
import com.example.streetsapplication.data.service.RemoteService
import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import com.example.streetsapplication.domain.service.RemoteServiceContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun provideStreetsRepository(repository: StreetsRepository): StreetsRepositoryContract

    @Binds
    abstract fun provideRemoteService(service: RemoteService): RemoteServiceContract
}