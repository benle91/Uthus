package hien.android.uthus.ui.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hien.android.uthus.data.FoodRecord
import hien.android.uthus.usecase.GetFoodRecordUseCase
import hien.android.uthus.usecase.InsertFoodRecordUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class MainViewModel(
    private val useCase: GetFoodRecordUseCase,
    private val roomUseCase: InsertFoodRecordUseCase
) : ViewModel() {

    val hashMapNumberRecord = HashMap<String, Int>()
    val hashMapExpireRecord = HashMap<String, Long>()
    var expiredTimeLiveData = MutableLiveData<Boolean>()

    val foodRecordsLiveData = MutableLiveData<List<FoodRecord>>()
    private val hashSetDuplicate = HashSet<String>()

    fun getFoodRecords() {
        viewModelScope.launch {
            val list = useCase.getFoodRecords()
            list.forEach { record ->
                val expireString = record.expiry
                val format = SimpleDateFormat("HH:mm:ss, dd-MM-yyyy")
                val secondExpire = format.parse(expireString).time - System.currentTimeMillis()
                hashMapExpireRecord[record.name] = secondExpire
            }
            foodRecordsLiveData.value = list
        }
    }

    fun save() {
        foodRecordsLiveData.value?.filter { hashMapNumberRecord.containsKey(it.name) }?.map {
            FoodRecord(
                name = it.name,
                _calories = it._calories,
                expiry = it.expiry,
                quantity = it.quantity,
                number = hashMapNumberRecord[it.name] ?: 0
            )
        }?.let { saveList ->
            viewModelScope.launch {
                roomUseCase.insertAll(saveList)
                getAll()
            }
        }
    }

    fun countDownTime() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                var tempHasNewExpired = false
                foodRecordsLiveData.value?.forEach {
                    hashMapExpireRecord[it.name]?.let { expireSecond ->
                        hashMapExpireRecord[it.name] = expireSecond - 1000
                        if (expireSecond < 1) {
                            if (hashSetDuplicate.add(it.name)) {
                                tempHasNewExpired = true
                            }
                        }
                    }
                }
                if (tempHasNewExpired) {
                    expiredTimeLiveData.postValue(true)
                }
            }
        }
    }

    private suspend fun getAll() {
        val list = roomUseCase.getAll()
        Log.i("ROOM-TEST", "Size = ${list.size}, Name = ${list.firstOrNull()?.name?:"EMPTY"}")

    }

}