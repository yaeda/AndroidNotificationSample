package com.github.yaeda.android.notificationsample

import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button.setOnClickListener {

      (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).also { manager ->
        manager.notify(0, with(NotificationCompat.Builder(this)) {
          setSmallIcon(R.drawable.notification_icon)
          color = Color.rgb(255, 128, 64)
          build()
        })
      }

    }
  }
}
