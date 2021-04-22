package android.example.yeahscience.data.spaceX.rockets.models

import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocketEntity.Companion.SPACEX_ROCKETS_TABLE_NAME
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SPACEX_ROCKETS_TABLE_NAME)
data class SpaceXRocketEntity (
    @PrimaryKey
    val id : String,
    val images : String?,
    val name : String,
    val type : String?,
    val stagesCount : Int,
    val costOfOneLaunch : Int,
    val dateOfFirstFlight : String?,
    val company: String,
    val description : String?
) {
    companion object {
        const val SPACEX_ROCKETS_TABLE_NAME = "rockets_entities_table"
    }
}