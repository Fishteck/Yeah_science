package android.example.yeahscience.data.spaceX.rockets.local

import android.content.Context
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocketEntity
import android.example.yeahscience.data.spaceX.rockets.models.SpaceXRocketEntity.Companion.SPACEX_ROCKETS_TABLE_NAME
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SpaceXRocketEntity::class], version = 2, exportSchema = false)
abstract class RocketsDatabase : RoomDatabase() {
    abstract fun getRocketsDAO() : RocketsDAO

    companion object {
        @Volatile private var instance: RocketsDatabase? = null

        fun getRocketDatabase(context: Context) : RocketsDatabase =
            instance ?: synchronized(this)
            {
                instance ?: buildDatabase(appContext = context)
                    .also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room
                .databaseBuilder(appContext, RocketsDatabase::class.java, SPACEX_ROCKETS_TABLE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}