package android.example.yeahscience.data.nasa.local

import android.example.yeahscience.data.nasa.models.NASAFrameToday
import android.example.yeahscience.data.nasa.models.NASAFrameToday.Companion.TABLE_NAME
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NASAPhotoTodayDAO {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getPhotoToday() : LiveData<NASAFrameToday>

    @Query("DELETE  FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(frameToday: NASAFrameToday)
}