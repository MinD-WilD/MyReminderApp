package by.tms.myreminderapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class StartScreenActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)

        CoroutineScope(Dispatchers.IO).launch {
            startPicture()
        }

    }
    suspend fun startPicture (){
        delay(2500)
        withContext(Dispatchers.Main){
            startActivity(Intent(this@StartScreenActivity, MainActivity::class.java))
        }
    }
}