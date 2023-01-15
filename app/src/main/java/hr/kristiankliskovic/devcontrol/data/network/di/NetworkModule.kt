package hr.kristiankliskovic.devcontrol.data.network.di

import android.util.Log
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.data.network.deviceService.DeviceService
import hr.kristiankliskovic.devcontrol.data.network.deviceService.DeviceServiceImpl
import hr.kristiankliskovic.devcontrol.data.network.userService.UserService
import hr.kristiankliskovic.devcontrol.data.network.userService.UserServiceImpl
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebsocketServiceImpl
import hr.kristiankliskovic.devcontrol.data.network.wsService.parser.WSDataParser
import hr.kristiankliskovic.devcontrol.data.network.wsService.parser.WSDataParserImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        Gson()
    }
    single<UserService> {
        UserServiceImpl(
            client = get(),
        )
    }
    single<DeviceService> {
        DeviceServiceImpl(
            client = get()
        )
    }
    single<WebSocketService> {
        WebsocketServiceImpl(
            ioDispatcher = Dispatchers.IO,
            gson = get(),
            wsDataParser = get()
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
    single<WSDataParser> {
        WSDataParserImpl(
            gson = get()
        )
    }
}
