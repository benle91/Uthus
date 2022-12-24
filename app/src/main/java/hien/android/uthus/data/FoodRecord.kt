package hien.android.uthus.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "tbl_food")
data class FoodRecord(
    @SerializedName("calories")
    val _calories: Int?,
    val expiry: String?,
    @PrimaryKey
    val name: String,
    val quantity: String?,
    val number: Int? = null
) {
    val calories: String
        get() = _calories.toString()
}