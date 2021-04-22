package android.example.yeahscience.data.nasa.models

import android.example.yeahscience.data.nasa.models.NASAFrameToday.Companion.TABLE_NAME
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = TABLE_NAME)
data class NASAFrameToday (
    @PrimaryKey
     val date: String ,
     val explanation: String,
    @SerializedName("hdurl")
     val photoHdurl: String?,
    @SerializedName("media_type")
     val mediaType: String,
     val title: String,
    @SerializedName("url")
     val photoUrl: String
) {
    companion object {
        const val TABLE_NAME = "nasa_photo_today_table"
    }
}