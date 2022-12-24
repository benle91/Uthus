package hien.android.uthus.core.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hien.android.uthus.data.FoodRecord

@Dao
interface FoodRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertFoodRecords(items: List<FoodRecord>)

    @Query("DELETE FROM tbl_food")
    suspend fun deleteAll()

    @Query("SELECT * FROM tbl_food")
    suspend fun getAll(): List<FoodRecord>

}