package org.sopt.sample.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.remote.AuthService
import org.sopt.sample.data.remote.ServicePool
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideAuthService(): AuthService {
        return ServicePool.authService
    }
}