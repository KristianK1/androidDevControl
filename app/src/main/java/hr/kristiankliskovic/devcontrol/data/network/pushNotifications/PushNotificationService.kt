package hr.kristiankliskovic.devcontrol.data.network.pushNotifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.DevControlApp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.data.repository.firebaseNotificationToken.FirebaseNotificationTokenRepository
import hr.kristiankliskovic.devcontrol.ui.main.MainActivity
import hr.kristiankliskovic.devcontrol.ui.main.channelId
import org.koin.android.ext.android.inject

data class FCMData(
    val data: String,
)

data class FCMCustomData(
    val title: String,
    val body: String,
)

class PushNotificationService(
) : FirebaseMessagingService() {
    private val firebaseNotificationTokenRepository: FirebaseNotificationTokenRepository by inject()

    init {
        Log.i("tokeeen", "init class")
        Toast.makeText(DevControlApp.application.applicationContext,
            "FCM class init",
            Toast.LENGTH_SHORT)
            .show()
//        getToken()
    }

    private fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i("tokeeen", "Fetching FCM registration token failed", task.exception)
                Toast.makeText(DevControlApp.application.applicationContext,
                    "Fetching FCM registration token failed",
                    Toast.LENGTH_SHORT)
                    .show()
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result

            Toast.makeText(DevControlApp.application.applicationContext, token, Toast.LENGTH_SHORT)
                .show()
            Log.i("tokeeen", token)
            firebaseNotificationTokenRepository.saveToken(token)
        })
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("tokeeen", token)
        firebaseNotificationTokenRepository.saveToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.i("tokeeen", "message recived")

        try {
            Log.i("tokeeen", Gson().toJson(remoteMessage))
            Log.i("tokeeen", Gson().toJson(remoteMessage.data))
            Log.i("tokeeen", Gson().toJson(remoteMessage.notification))

            for(property in remoteMessage.data.values){
                val parsed: FCMCustomData = Gson().fromJson(property, FCMCustomData::class.java)
                Log.i("tokeeen", "title_|${parsed.title}|")
                Log.i("tokeeen", "bodyy_|${parsed.body}|")
                sendNotification(parsed.title, parsed.body)
            }


        } catch (e: Throwable) {
            Log.i("tokeeen", e.message.toString())
        }

    }

    private fun sendNotification(title: String, messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this,
            0 /* Request code */,
            intent,
            PendingIntent.FLAG_MUTABLE)

        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.connected_icon_foreground)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}
