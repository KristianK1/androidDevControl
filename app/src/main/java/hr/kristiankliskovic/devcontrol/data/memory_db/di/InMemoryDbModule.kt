package hr.kristiankliskovic.devcontrol.data.memory_db.di

import hr.kristiankliskovic.devcontrol.data.memory_db.InMemoryDb
import hr.kristiankliskovic.devcontrol.data.memory_db.LoggedInUserDao
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val InMemoryDbModule = module {
    single<LoggedInUserDao> {
        InMemoryDb(
            ioDispatcher = Dispatchers.IO
        )
    }
}
