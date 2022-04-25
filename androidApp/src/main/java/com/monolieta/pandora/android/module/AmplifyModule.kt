package com.monolieta.pandora.android.module

import android.content.Context
import com.monolieta.pandora.Amplify
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AmplifyModule {

    @Singleton
    @Provides
    fun provideAmplify(@ApplicationContext context: Context): Amplify = Amplify(context)
}