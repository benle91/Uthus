package hien.android.uthus.core.di

import androidx.room.Room
import hien.android.uthus.core.room.AppDatabase
import hien.android.uthus.core.room.dao.FoodRecordDao
import hien.android.uthus.repository.RoomFoodRecordRepository
import hien.android.uthus.repository.RoomFoodRecordRepositoryImp
import hien.android.uthus.ui.activity.MainViewModel
import hien.android.uthus.usecase.GetFoodRecordUseCase
import hien.android.uthus.usecase.InsertFoodRecordUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val injectionModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "job"
        ).build()
    }
    single<FoodRecordDao> { get<AppDatabase>().getFoodRecordDao() }
    factory { GetFoodRecordUseCase() }
    single<RoomFoodRecordRepository> { RoomFoodRecordRepositoryImp(get()) }
    factory { InsertFoodRecordUseCase(get()) }
    viewModel { MainViewModel(get(), get()) }
}