package org.sopt.sample.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.remote.AuthService
import org.sopt.sample.repositories.AuthRepository
import org.sopt.sample.repositories.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Binds
    fun bindsAuthRepository(apiInterface: AuthService): AuthRepository {
        return AuthRepositoryImpl(apiInterface)
    }
}