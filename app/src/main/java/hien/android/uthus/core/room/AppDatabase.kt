package hien.android.uthus.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hien.android.uthus.core.room.dao.FoodRecordDao
import hien.android.uthus.data.FoodRecord

@Database(entities = [FoodRecord::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFoodRecordDao(): FoodRecordDao
}