package android.example.yeahscience.di

import android.example.yeahscience.data.iss.remote.ISSService
import android.example.yeahscience.data.iss.remote.RemoteISSDataSource
import android.example.yeahscience.data.nasa.remote.NasaService
import android.example.yeahscience.data.nasa.remote.RemoteNASADataSource
import android.example.yeahscience.data.spaceX.remote.RemoteSpaceXDataSource
import android.example.yeahscience.data.spaceX.remote.SpaceXService
import android.example.yeahscience.utils.network.RemoteContract
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class )
object RemoteModule {

    @Provides
    fun provideInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient( interceptor: HttpLoggingInterceptor) : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @Named("retrofit_spaceX")
    fun provideRetrofitSpaceX(gson: Gson, okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteContract.SPACEX_API)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()

    @Provides
    @Singleton
    @Named("retrofit_NASA")
    fun provideRetrofitNASA(gson: Gson, okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteContract.NASA_API)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()

    @Provides
    fun provideNasaService(@Named("retrofit_NASA")retrofit: Retrofit) : NasaService =
        retrofit.create( NasaService::class.java)


    @Provides
    fun provideNasaRemoteDataSource(nasaService: NasaService)  : RemoteNASADataSource =
        RemoteNASADataSource(
            nasaService
        )

    @Provides
    fun provideRocketsService(@Named("retrofit_spaceX")retrofit: Retrofit) : SpaceXService =
        retrofit.create( SpaceXService::class.java)


    @Provides
   fun provideRocketsRemoteDataSource(spaceXService: SpaceXService)  : RemoteSpaceXDataSource =
        RemoteSpaceXDataSource(
            spaceXService
        )

    @Provides
    @Singleton
    @Named("retrofit_ISS")
    fun provideRetrofitISS(gson: Gson, okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(RemoteContract.ISS_API)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()

    @Provides
    fun provideISSService(@Named("retrofit_ISS")retrofit: Retrofit) : ISSService =
        retrofit.create( ISSService::class.java)


    @Provides
    fun provideISSRemoteDataSource(issService: ISSService)  : RemoteISSDataSource =
        RemoteISSDataSource(
            issService
        )
}