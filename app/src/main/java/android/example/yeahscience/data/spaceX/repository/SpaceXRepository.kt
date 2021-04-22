package android.example.yeahscience.data.spaceX.repository

import android.example.yeahscience.data.spaceX.rockets.local.RocketsDAO
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocket
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocketEntity
import android.example.yeahscience.data.spaceX.remote.RemoteSpaceXDataSource
import android.example.yeahscience.utils.performFetchOperation
import android.example.yeahscience.utils.performLocalOperation
import android.example.yeahscience.utils.performRemoteOperation
import androidx.lifecycle.map
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class SpaceXRepository @Inject constructor(
    private val gson: Gson,
    private val remoteSpaceXDataSource: RemoteSpaceXDataSource,
    private val localDataSource: RocketsDAO
) {

    private val type: Type = object : TypeToken<List<String>>() {}.type

    fun getRockets() = performFetchOperation(
        databaseQuery = {
            localDataSource
                .getAllRockets().map {
                    it.map { spaceXRocketEntity ->
                        SpaceXRocket(
                            id = spaceXRocketEntity.id,
                            images = gson.fromJson<List<String>>(spaceXRocketEntity.images, type),
                            name = spaceXRocketEntity.name,
                            type = spaceXRocketEntity.type,
                            stagesCount = spaceXRocketEntity.stagesCount,
                            costOfOneLaunch = spaceXRocketEntity.costOfOneLaunch,
                            dateOfFirstFlight = spaceXRocketEntity.dateOfFirstFlight,
                            company = spaceXRocketEntity.company,
                            description = spaceXRocketEntity.description
                        )
                    }
                }
        },
        networkCall = { remoteSpaceXDataSource.getRockets() },
        saveCallResult = {
            localDataSource
                .insertAll(it.map
                { spaceXRocket ->
                    SpaceXRocketEntity(
                        id = spaceXRocket.id,
                        images = gson.toJson(spaceXRocket.images),
                        name = spaceXRocket.name,
                        type = spaceXRocket.type,
                        stagesCount = spaceXRocket.stagesCount,
                        costOfOneLaunch = spaceXRocket.costOfOneLaunch,
                        dateOfFirstFlight = spaceXRocket.dateOfFirstFlight,
                        company = spaceXRocket.company,
                        description = spaceXRocket.description
                    )
                })

        }
    )

    fun getRocket(id : String) =
        performLocalOperation { localDataSource.getRocket(id).map {
                spaceXRocketEntity ->
            SpaceXRocket(
                id = spaceXRocketEntity.id,
                images = gson.fromJson<List<String>>(spaceXRocketEntity.images, type),
                name = spaceXRocketEntity.name,
                type = spaceXRocketEntity.type,
                stagesCount = spaceXRocketEntity.stagesCount,
                costOfOneLaunch = spaceXRocketEntity.costOfOneLaunch,
                dateOfFirstFlight = spaceXRocketEntity.dateOfFirstFlight,
                company = spaceXRocketEntity.company,
                description = spaceXRocketEntity.description
            )
        } }


    fun getCompanyInfo()  =
        performRemoteOperation { remoteSpaceXDataSource.getCompanyInfo() }


}