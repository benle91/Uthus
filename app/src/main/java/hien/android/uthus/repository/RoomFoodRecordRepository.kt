package hien.android.uthus.repository

import hien.android.uthus.data.FoodRecord

interface RoomFoodRecordRepository {

    suspend fun deleteAll()

    suspend fun insertRecords(list: List<FoodRecord>)

    suspend fun getAll(): List<FoodRecord>

}