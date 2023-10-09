package com.example.appy.main.di

import com.example.appy.main.repository.TextRepository
import com.example.appy.main.room.AppDatabase
import com.example.appy.main.viewmodel.TextViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    viewModel {
        TextViewModel(repository = get())
    }

    single {
        TextRepository(textDao = get<AppDatabase>().textDao())
    }

    single {
        AppDatabase.getInstance(context = androidContext())
    }
}