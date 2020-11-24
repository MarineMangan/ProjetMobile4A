package com.example.projetmobile4a.injection

import android.content.Context
import androidx.room.Room
import com.example.projetmobile4a.data.local.AppDatabase
import com.example.projetmobile4a.data.local.DatabaseDao
import com.example.projetmobile4a.data.repository.UserRepository
import com.example.projetmobile4a.domain.usecase.CreateUserUseCase
import com.example.projetmobile4a.domain.usecase.GetUserUseCase
import com.example.projetmobile4a.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.security.AccessControlContext

val presentationModule = module {
    factory { MainViewModel(get(), get()) }
}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule = module {
    single {UserRepository(get())}
    single { createDataBase(androidContext()) }
}

fun createDataBase(context: Context): DatabaseDao {
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return appDatabase.databaseDao()
}
