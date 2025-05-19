package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class TestTouchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test_touch)
        val passButton = findViewById<Button>(R.id.btn_passed)

        lifecycleScope.launch {
            val result = withTimeoutOrNull(10000) {
                while (true) {
                    delay(100)
                }
            }

            if (result == null) {
                Toast.makeText(this@TestTouchActivity, "Não passou no Teste", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        passButton.setOnClickListener {
            Toast.makeText(this, "Passou no teste", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}