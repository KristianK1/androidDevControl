package hr.kristiankliskovic.devcontrol.data.network.pushNotifications

import android.util.Log
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService() : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val title = remoteMessage.notification?.title
        val text = remoteMessage.notification?.body
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("tokeeen", token)
    }
}
