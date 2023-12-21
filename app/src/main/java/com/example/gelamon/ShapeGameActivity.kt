package com.example.gelamon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class ShapeGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape_game)

        // Handle the button click or any action to retry the game
        val homeButton = findViewById<Button>(R.id.homeButton)
        homeButton.setOnClickListener {
            // Restart the SecondGameActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

