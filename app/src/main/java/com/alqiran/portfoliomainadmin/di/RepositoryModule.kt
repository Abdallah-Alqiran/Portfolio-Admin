package com.alqiran.portfoliomainadmin.di

import com.alqiran.portfoliomainadmin.data.datasourses.remote.repository.FirebaseRepositoryImpl
import com.alqiran.portfoliomainadmin.domain.repository.FirebaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(impl: FirebaseRepositoryImpl): FirebaseRepository
}