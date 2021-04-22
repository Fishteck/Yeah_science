package android.example.yeahscience.data.nasa.remote

import android.example.yeahscience.data.nasa.models.NASAFrameToday
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {
    @GET("https://api.nasa.gov/planetary/apod?")
        suspend fun fetchTodayPhoto(
        @Query("api_key")  apiKey : String
    ) : Response<NASAFrameToday>
}