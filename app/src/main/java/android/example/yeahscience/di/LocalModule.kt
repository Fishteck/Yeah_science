package android.example.yeahscience.di

import android.content.Context
import android.example.yeahscience.data.nasa.local.NASAPhotoTodayDAO
import android.example.yeahscience.data.nasa.local.NASAPhotoTodayDatabase
import android.example.yeahscience.data.spaceX.rockets.local.RocketsDAO
import android.example.yeahscience.data.spaceX.rockets.local.RocketsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRocketsDatabase( @ApplicationContext appContext: Context) : RocketsDatabase=
        RocketsDatabase.getRocketDatabase(appContext)

    @Provides
    @Singleton
    fun provideRocketsDAO(db: RocketsDatabase) : RocketsDAO = db.getRocketsDAO()

    @Provides
    @Singleton
    fun provideNASAPhotoTodayDatabase( @ApplicationContext appContext: Context) : NASAPhotoTodayDatabase =
        NASAPhotoTodayDatabase.getNasaPhotoTodayDatabase(appContext)

    @Provides
    @Singleton
    fun provideNASAPhotoTodayDAO(db: NASAPhotoTodayDatabase) : NASAPhotoTodayDAO = db.getNasaPhotoTodayDAO()


}