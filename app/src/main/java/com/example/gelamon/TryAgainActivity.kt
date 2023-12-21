package com.example.gelamon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class TryAgainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_try_again)

        val level = intent.getIntExtra("LEVEL", 0)

        val tryAgainButton = findViewById<CardView>(R.id.tryAgainButton)
        tryAgainButton.setOnClickListener {
            val intent = when (level) {
                2 -> Intent(this, SecondGameActivity::class.java)
                3 -> Intent(this, ThirdGameActivity::class.java)
                // Add more cases if you have additional game levels
                else -> Intent(this, MainActivity::class.java) // Default to main if level unknown
            }
            startActivity(intent)
            finish()
        }
        val backToHomeButton = findViewById<CardView>(R.id.backToHomeButton)
        backToHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}


