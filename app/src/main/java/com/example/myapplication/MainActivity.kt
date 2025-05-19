package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val enterButton = findViewById<Button>(R.id.btn_enter_test)
        enterButton.setOnClickListener {
            val intent = Intent(this, TestTouchActivity::class.java)
            startActivity(intent)
        }
    }
}