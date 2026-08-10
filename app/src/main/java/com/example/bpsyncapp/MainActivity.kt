package com.example.bpsyncapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val textView = TextView(this).apply {
            text = "BP Sync App Running\nListening for Watch Sensor Readings..."
            textSize = 18f
            setPadding(32, 32, 32, 32)
        }
        setContentView(textView)

        val serviceIntent = Intent(this, WatchSensorService::class.java)
        startService(serviceIntent)
    }
}
