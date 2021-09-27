package com.example.coroutinesbasic

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MyTag", "onCreate: ThreadName: ${Thread.currentThread().name}")
        val coroutinesButton = findViewById<Button>(R.id.coroutinesButton)
        val coroutinesImage = findViewById<ImageView>(R.id.coroutinesImage)
        coroutinesButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Log.e("MyTag", "onCreate: ThreadName: ${Thread.currentThread().name}")
                    val url = URL("https://i.redd.it/bfc0pz8qdji61.jpg")
                    val bitmap = BitmapFactory.decodeStream(url.openStream())
                    withContext(Dispatchers.Main) {
                        coroutinesImage.setImageBitmap(bitmap)
                        Log.d(
                            "MyTag",
                            "onCreateWithContext: ThreadName: ${Thread.currentThread().name}"
                        )
                    }
 
                } catch (e: Exception) {
                    Log.d(
                        "MyTag",
                        "Exception: ThreadName: ${Thread.currentThread().name}"
                    )
                }
            }


        }
    }
}