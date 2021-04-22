package android.example.yeahscience.data.spaceX.rockets.local

import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocketEntity
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocketEntity.Companion.SPACEX_ROCKETS_TABLE_NAME
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RocketsDAO {

    @Query("SELECT * FROM $SPACEX_ROCKETS_TABLE_NAME")
    fun getAllRockets() : LiveData<List<SpaceXRocketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rockets: List<SpaceXRocketEntity>)

    @Query("SELECT * FROM $SPACEX_ROCKETS_TABLE_NAME WHERE id = :id")
        fun getRocket(id: String) : LiveData<SpaceXRocketEntity>

}