package hr.kristiankliskovic.devcontrol.data.network

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.network.userService.UserService
import hr.kristiankliskovic.devcontrol.data.network.userService.UserServiceImpl
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebsocketServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.time.Duration
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<UserService> {
        UserServiceImpl(
            client = get(),
        )
    }
    single<WebsocketServiceImpl> {
        WebsocketServiceImpl(
            ioDispatcher = Dispatchers.IO,
            client = get()
        )
    }
    single {
        HttpClient(Android) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HTTP", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}
