package android.example.yeahscience.data.iss.remote

import android.example.yeahscience.utils.network.BaseDataSource
import javax.inject.Inject

class RemoteISSDataSource @Inject constructor( private  val service : ISSService) : BaseDataSource(){
    suspend fun getISSLocation() = getResult { service.fetchISSLocation() }
}