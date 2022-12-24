package hien.android.uthus.usecase

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import hien.android.uthus.data.FoodRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFoodRecordUseCase {
    private val string = "[\n" +
            " {\n" +
            "  \"name\": \"Acai Juice\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 139,\n" +
            "  \"expiry\": \"14:48:50, 24-12-2022\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Aloe Vera\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 106,\n" +
            "  \"expiry\": \"14:35:20, 24-12-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Apple Juice\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 110,\n" +
            "  \"expiry\": \"14:33:00, 24-12-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Apricot Nectar\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 134,\n" +
            "  \"expiry\": \"00:00:00, 01-04-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Banana Juice\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 120,\n" +
            "  \"expiry\": \"00:00:00, 01-05-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Blackberry Juice\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 115,\n" +
            "  \"expiry\": \"00:00:00, 01-06-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Boysenberry Juice\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 130,\n" +
            "  \"expiry\": \"00:00:00, 01-07-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Capri-Sun\",\n" +
            "  \"quantity\": \"1 capri-sun (200 ml)\",\n" +
            "  \"calories\": 82,\n" +
            "  \"expiry\": \"00:00:00, 01-08-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Carrot Juice\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 96,\n" +
            "  \"expiry\": \"00:00:00, 01-09-2023\"\n" +
            " },\n" +
            " {\n" +
            "  \"name\": \"Chamomile Tea\",\n" +
            "  \"quantity\": \"8 fl. oz. (240 ml)\",\n" +
            "  \"calories\": 0,\n" +
            "  \"expiry\": \"00:00:00, 01-10-2023\"\n" +
            " }\n" +
            "]\n"

    suspend fun getFoodRecords(): List<FoodRecord> {
        return try {
            val gson = GsonBuilder().create()
            gson.fromJson(string, object : TypeToken<List<FoodRecord>>() {}.type)
        } catch (ex: Exception) {
            emptyList<FoodRecord>()
        }
    }
}