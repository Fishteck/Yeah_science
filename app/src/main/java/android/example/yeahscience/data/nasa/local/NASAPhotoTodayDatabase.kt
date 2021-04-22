package android.example.yeahscience.data.nasa.local

import android.content.Context
import android.example.yeahscience.data.nasa.models.NASAFrameToday
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NASAFrameToday::class], version = 2, exportSchema = false)
abstract class NASAPhotoTodayDatabase : RoomDatabase(){

    abstract fun getNasaPhotoTodayDAO(): NASAPhotoTodayDAO

    companion object {
        @Volatile private var instance: NASAPhotoTodayDatabase? = null

        fun getNasaPhotoTodayDatabase(context: Context) : NASAPhotoTodayDatabase =
            instance ?: synchronized(this)
            {
                instance ?: buildDatabase(appContext = context)
                    .also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room
                .databaseBuilder(appContext, NASAPhotoTodayDatabase::class.java,
                    NASAFrameToday.TABLE_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
    }
}