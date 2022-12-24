package hien.android.uthus.usecase

import hien.android.uthus.data.FoodRecord
import hien.android.uthus.repository.RoomFoodRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertFoodRecordUseCase(private val repository: RoomFoodRecordRepository) {

    suspend fun insertAll(list: List<FoodRecord>) {
        withContext(Dispatchers.IO) {
            repository.deleteAll()
            repository.insertRecords(list)
        }
    }

    suspend fun getAll(): List<FoodRecord> {
        return withContext(Dispatchers.IO) {
            repository.getAll()
        }
    }

}