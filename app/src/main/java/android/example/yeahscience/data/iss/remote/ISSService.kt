package android.example.yeahscience.data.iss.remote


import android.example.yeahscience.data.iss.models.ISSCurrentLocationResponse
import retrofit2.Response
import retrofit2.http.GET

interface ISSService {
    @GET("iss-now.json")
    suspend fun fetchISSLocation() : Response<ISSCurrentLocationResponse>
}