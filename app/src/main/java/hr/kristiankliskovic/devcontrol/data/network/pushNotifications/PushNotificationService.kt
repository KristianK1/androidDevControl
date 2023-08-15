package hr.kristiankliskovic.devcontrol.data.network.pushNotifications

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import hr.kristiankliskovic.devcontrol.DevControlApp
import hr.kristiankliskovic.devcontrol.data.repository.firebaseNotificationToken.FirebaseNotificationTokenRepository
import hr.kristiankliskovic.devcontrol.ui.main.MainScreen
import hr.kristiankliskovic.devcontrol.ui.main.MainScreenViewModel
import org.koin.android.ext.android.inject


class PushNotificationService(
) : FirebaseMessagingService() {
    private val firebaseNotificationTokenRepository: FirebaseNotificationTokenRepository by inject()

    init {
        Log.i("tokeeen", "init class")
        getToken()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.i("tokeeen", "message recived")
        if(remoteMessage.data.isNotEmpty()){
//            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("tokeeen", token)
        firebaseNotificationTokenRepository.saveToken(token)
    }

    fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i("tokeeen", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result

            Toast.makeText(DevControlApp.application.applicationContext, token, Toast.LENGTH_SHORT).show()
            Log.i("tokeeen", token)
            firebaseNotificationTokenRepository.saveToken(token)
        })
    }
}
