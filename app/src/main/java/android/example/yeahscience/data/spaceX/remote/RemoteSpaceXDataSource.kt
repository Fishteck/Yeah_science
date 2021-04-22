package android.example.yeahscience.data.spaceX.remote

import android.example.yeahscience.utils.network.BaseDataSource
import javax.inject.Inject

class RemoteSpaceXDataSource @Inject constructor(
    private val spaceXService: SpaceXService
) : BaseDataSource() {

    suspend fun getRockets() = getResult { spaceXService.fetchAllRockets() }

    suspend fun getCompanyInfo() = getResult { spaceXService.fetchCompanyInfo() }
}