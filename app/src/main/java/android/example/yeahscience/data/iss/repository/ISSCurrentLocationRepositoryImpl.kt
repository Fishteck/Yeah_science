package android.example.yeahscience.data.iss.repository


import android.example.yeahscience.data.iss.models.ISSCurrentLocationResponse
import android.example.yeahscience.data.iss.remote.RemoteISSDataSource
import android.example.yeahscience.utils.Resource
import android.example.yeahscience.utils.performRemoteOperation
import androidx.lifecycle.LiveData
import javax.inject.Inject

class ISSCurrentLocationRepositoryImpl @Inject constructor(
    private  val remoteISSDataSource: RemoteISSDataSource
) : ISSCurrentLocationRepository<ISSCurrentLocationResponse> {
    override fun getISSCurrentLocation(): LiveData<Resource<ISSCurrentLocationResponse>> =
        performRemoteOperation(
            networkCall = { remoteISSDataSource.getISSLocation() }
        )
}

