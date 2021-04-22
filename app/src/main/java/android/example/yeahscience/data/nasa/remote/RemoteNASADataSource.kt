package android.example.yeahscience.data.nasa.remote

import android.example.yeahscience.utils.network.BaseDataSource
import android.example.yeahscience.utils.network.RemoteContract.Companion.NASA_API_KEY
import javax.inject.Inject

class RemoteNASADataSource @Inject constructor
    (private val service: NasaService)
    : BaseDataSource() {

    suspend fun getPhotoToday () = getResult { service.fetchTodayPhoto(NASA_API_KEY) }
}