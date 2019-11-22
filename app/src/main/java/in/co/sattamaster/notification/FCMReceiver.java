package in.co.sattamaster.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import in.co.sattamaster.R;
import in.co.sattamaster.ui.Homepage.MainActivity;
import timber.log.Timber;

public class FCMReceiver extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);


    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() != null) {
            showNotification(remoteMessage);

            Timber.d("Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

    private void showNotification(RemoteMessage messageBody) {
    /*
    Creates pending intent
     */
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        long[] v = {500,1000};
       // notificationBuilder.setVibrate(v);

        intent.putExtra("fromNotification", true);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
      //  Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)

                .setContentTitle(messageBody.getNotification().getTitle())
                .setContentText(messageBody.getNotification().getBody())
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setVibrate(v)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
               // .setSound(defaultSoundUri)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        long time = System.currentTimeMillis();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notificationId = Integer.valueOf(last4Str);
      //  Log.d("Notification Id", notificationId + "");
        notificationManager.notify(notificationId /* ID of notification */,
                notificationBuilder.build());
    }


    private void sendTokenBroadcast(String tokenVal) {
     //   Log.d(TAG, "Sending token broadcast: " + tokenVal);
    }

    @Nullable
    static String extractPayloadData(RemoteMessage message, String key) {
        return message.getData().get(key);

    }


}

