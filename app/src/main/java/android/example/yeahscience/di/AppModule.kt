package android.example.yeahscience.di

import android.example.yeahscience.data.iss.models.ISSCurrentLocationResponse
import android.example.yeahscience.data.iss.remote.RemoteISSDataSource
import android.example.yeahscience.data.iss.repository.ISSCurrentLocationRepository
import android.example.yeahscience.data.iss.repository.ISSCurrentLocationRepositoryImpl
import android.example.yeahscience.data.nasa.local.NASAPhotoTodayDAO
import android.example.yeahscience.data.nasa.remote.RemoteNASADataSource
import android.example.yeahscience.data.nasa.repository.NASARepository
import android.example.yeahscience.data.spaceX.rockets.local.RocketsDAO
import android.example.yeahscience.data.spaceX.remote.RemoteSpaceXDataSource
import android.example.yeahscience.data.spaceX.repository.SpaceXRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRocketsRepository(
        gson : Gson,
        remoteSpaceXDataSource: RemoteSpaceXDataSource,
        localDataSource: RocketsDAO
    ) = SpaceXRepository(
        gson = gson,
        remoteSpaceXDataSource = remoteSpaceXDataSource,
        localDataSource = localDataSource
    )

    @Provides
    fun provideNASAPhotoTodayRepository(
        remoteNASADataSource: RemoteNASADataSource,
        localNASADataSource: NASAPhotoTodayDAO
    ) = NASARepository(
        remoteNASADataSource = remoteNASADataSource,
        localNASADataSource = localNASADataSource
    )


    @Provides
    fun provideISSRepository(
        remoteISSDataSource: RemoteISSDataSource
    ) : ISSCurrentLocationRepository<ISSCurrentLocationResponse> = ISSCurrentLocationRepositoryImpl(
        remoteISSDataSource = remoteISSDataSource
    )
}