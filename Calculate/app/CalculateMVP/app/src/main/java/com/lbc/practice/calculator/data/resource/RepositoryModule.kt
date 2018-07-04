package com.lbc.practice.calculator.data.resource

import com.lbc.practice.calculator.data.resource.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): DataSource

}
