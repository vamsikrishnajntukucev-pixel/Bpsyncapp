package com.example.bpsyncapp

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class PhoneWearableListenerService : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path == "/health_data_path") {
            val rawData = String(messageEvent.data, StandardCharsets.UTF_8)
            try {
                val json = JSONObject(rawData)
                val timestamp = json.getLong("timestamp")
                val heartRate = json.getDouble("heartRate")
                val systolic = json.optInt("estimatedSystolic", 120)
                val diastolic = json.optInt("estimatedDiastolic", 80)

                Log.d("BPSyncApp", "Received Reading -> HR: $heartRate | BP: $systolic/$diastolic at $timestamp")
            } catch (e: Exception) {
                Log.e("BPSyncApp", "Error processing data payload", e)
            }
        } else {
            super.onMessageReceived(messageEvent)
        }
    }
}
