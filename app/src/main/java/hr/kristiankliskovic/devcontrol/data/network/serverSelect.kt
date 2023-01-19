package hr.kristiankliskovic.devcontrol.data.network

data class Server(
    val httpServer: String,
    val wsServer: String,
)

val SB_PC_WiFi = Server(
    httpServer = "http://192.168.1.150:8000",
    wsServer = "ws://192.168.1.150:8000"
)

val SB_PC_LAN = Server(
    httpServer = "http://192.168.1.70:8000",
    wsServer = "192.168.1.70"
)

val renderHosting = Server(
    httpServer = "https://devcontrol-backend.onrender.com",
    wsServer = "devcontrol-backend.onrender.com"
)

val herokuHosting = Server(
    httpServer = "https://devcontrol.herokuapp.com",
    wsServer = "devcontrol.herokuapp.com"
)
val HTTPSERVER = renderHosting
