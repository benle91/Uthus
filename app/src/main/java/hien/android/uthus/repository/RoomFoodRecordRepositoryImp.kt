package hien.android.uthus.repository

import hien.android.uthus.core.room.dao.FoodRecordDao
import hien.android.uthus.data.FoodRecord

class RoomFoodRecordRepositoryImp(private val dao: FoodRecordDao) : RoomFoodRecordRepository {
    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun insertRecords(list: List<FoodRecord>) {
        dao.insertFoodRecords(list)
    }

    override suspend fun getAll(): List<FoodRecord> {
        return dao.getAll()
    }
}