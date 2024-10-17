package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.example.project.AppDatabase
import org.example.project.data.ExpenseRepoImpl
import org.example.project.domain.ExpenseRepository
import org.example.project.presentation.ExpensesViewModel
import org.koin.dsl.module

fun appModule(appDatabase: AppDatabase) = module {
    single<HttpClient> { HttpClient { install(ContentNegotiation) { json() } }  }
    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase, get()) }
    factory { ExpensesViewModel(get()) }
}