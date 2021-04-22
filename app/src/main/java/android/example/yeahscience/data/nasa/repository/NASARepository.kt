package android.example.yeahscience.data.nasa.repository

import android.example.yeahscience.data.nasa.local.NASAPhotoTodayDAO
import android.example.yeahscience.data.nasa.remote.RemoteNASADataSource
import android.example.yeahscience.utils.performFetchOperation
import javax.inject.Inject

class NASARepository @Inject constructor(
    private  val remoteNASADataSource: RemoteNASADataSource,
    private val localNASADataSource: NASAPhotoTodayDAO
) {

    fun getPhotoToday() = performFetchOperation(
        databaseQuery = { localNASADataSource.getPhotoToday() },
        networkCall = { remoteNASADataSource.getPhotoToday() },
        saveCallResult = { localNASADataSource.insert(it) },
        deleteLocalData = { localNASADataSource.deleteAll()}
    )
}