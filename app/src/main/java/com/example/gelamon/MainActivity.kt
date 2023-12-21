package com.example.gelamon

import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberButton: CardView = findViewById(R.id.numberButton)
        numberButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }
        val shapeButton: CardView = findViewById(R.id.shapeButton)
        shapeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ShapeGameActivity::class.java)
            startActivity(intent)
        }
    }
}

