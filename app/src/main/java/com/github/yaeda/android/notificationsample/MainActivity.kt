package com.github.yaeda.android.notificationsample

import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.NotificationCompat
import android.view.WindowManager
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

  private var notificationColor = Color.rgb(255, 128, 64)
  private var statusBarColor = Color.rgb(255, 128, 64)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btn_notify.setOnClickListener {
      sendNotification()
    }

    btn_notification_color.setOnClickListener {
      ColorPickerDialogBuilder
          .with(this)
          .initialColor(notificationColor)
          .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
          .density(8)
          .setTitle("Notification Color")
          .showColorPreview(true)
          .showAlphaSlider(false)
          .setPositiveButton("ok", { _, selectedColor, _ ->
            notificationColor = selectedColor
            sendNotification()
          })
          .build()
          .show()
    }

    btn_status_bar_color.setOnClickListener {
      ColorPickerDialogBuilder
          .with(this)
          .initialColor(statusBarColor)
          .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
          .density(8)
          .setTitle("Status Bar Color")
          .showColorPreview(true)
          .showAlphaSlider(false)
          .setPositiveButton("ok", { _, selectedColor, _ ->
            statusBarColor = selectedColor
            changeStatusBarColor()
          })
          .build()
          .show()
    }
  }

  private fun sendNotification() {
    (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).also { manager ->
      manager.notify(0, with(NotificationCompat.Builder(this)) {
        setSmallIcon(R.drawable.notification_icon)
        color = notificationColor
        build()
      })
    }
  }

  private fun changeStatusBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
      window.statusBarColor = statusBarColor
    }
  }
}