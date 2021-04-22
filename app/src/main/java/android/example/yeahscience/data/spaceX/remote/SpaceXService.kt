package android.example.yeahscience.data.spaceX.remote

import android.example.yeahscience.data.spaceX.aboutCompany.models.SpaceXInfo
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocket
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXService {
    @GET("rockets")
    suspend fun fetchAllRockets() : Response<List<SpaceXRocket>>

    @GET("company")
    suspend fun fetchCompanyInfo() : Response<SpaceXInfo>
}