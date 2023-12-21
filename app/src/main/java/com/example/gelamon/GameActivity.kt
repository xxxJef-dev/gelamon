package com.example.gelamon

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {

    private var expectedNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val closeButton = findViewById<Button>(R.id.closeButton)
        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        // Create a list of numbers from 1 to 10
        val numberList = (1..10).toList().shuffled()

        for (i in 0 until gridLayout.childCount) {
            val cardView = gridLayout.getChildAt(i) as? CardView
            val textView = cardView?.findViewById<TextView>(R.id.textView)

            // Assign a shuffled number to each cardView's textView
            textView?.text = numberList[i].toString()

            cardView?.setOnClickListener {
                handleCardClick(it as CardView)
            }
        }
    }

    private fun handleCardClick(cardView: CardView) {
        val currentNumber = (cardView.findViewById<TextView>(R.id.textView)?.text ?: "0").toString().toInt()

        if (currentNumber == expectedNumber) {
            // Change background or text color to indicate correctness
            cardView.setCardBackgroundColor(Color.parseColor("#40b540"))
            val textView = cardView.findViewById<TextView>(R.id.textView)
            textView.setTextColor(Color.WHITE)

            if (expectedNumber == 10) {
                showCongratsMessage()
            } else {
                expectedNumber++
            }
        } else {
            showTipMessage()
        }
    }

    private fun showCongratsMessage() {
        Toast.makeText(this, "Congratulations! You completed the sequence.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SecondGameActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
        // Do something when the user completes the game (e.g., navigate to another activity)
    }

    private fun showTipMessage() {
        val tipMessage = "Tap number $expectedNumber"
        Toast.makeText(this, tipMessage, Toast.LENGTH_SHORT).show()
    }
}

