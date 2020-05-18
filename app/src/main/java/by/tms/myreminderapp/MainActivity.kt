package by.tms.myreminderapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import by.tms.myreminderapp.R.layout.activity_main
import by.tms.myreminderapp.R.layout.mtrl_calendar_year
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import java.util.Calendar.*


class MainActivity : AppCompatActivity() {
    var years = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)



        datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            years = year
            month = monthOfYear
            day = dayOfMonth
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChanel()
        }




        timePicker.setOnTimeChangedListener { view, pickerHour, pickerMinute ->
            hour = pickerHour
            minute = pickerMinute
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChanel()
        }

        remind_button.setOnClickListener {
            val calendarNow = Calendar.getInstance()
            val calenderAlarm = Calendar.getInstance()
            calenderAlarm.set(Calendar.YEAR, years)
            calenderAlarm.set(Calendar.MONTH, month)
            calenderAlarm.set(Calendar.DAY_OF_MONTH, day)
            calenderAlarm.set(Calendar.HOUR_OF_DAY, hour)
            calenderAlarm.set(Calendar.MINUTE, minute)
            calenderAlarm.set(Calendar.SECOND, 0)

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent = Intent(this, Notification::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 999, intent, 0)

            if (calenderAlarm.after(calendarNow)) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calenderAlarm.timeInMillis, pendingIntent)

            }


            Toast.makeText(this, "Напомнилка начала работу", Toast.LENGTH_SHORT).show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChanel() {
        val name = "Reminder"
        val description = "Chanel for reminder"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val chanel = NotificationChannel(NOTIFICATION, name, importance)
        chanel.description = description
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.createNotificationChannel(chanel)
    }


}




