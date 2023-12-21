package com.example.gelamon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class SecondGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_game)

        val closeButton = findViewById<Button>(R.id.closeButton)
        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        val option1CardView = findViewById<CardView>(R.id.option1Button)
        val option2CardView = findViewById<CardView>(R.id.option2Button)
        val option3CardView = findViewById<CardView>(R.id.option3Button)
        val option4CardView = findViewById<CardView>(R.id.option4Button)

        // Generate a random number sequence question
        val randomNumber = (1..10).random()
        val question = "What comes next after $randomNumber?"
        questionTextView.text = question

        // Generate multiple-choice options including the correct answer
        val correctAnswer = randomNumber + 1
        val incorrectAnswer1 = (1..10).filter { it != correctAnswer }.random()
        val incorrectAnswer2 = (1..10).filter { it != correctAnswer && it != incorrectAnswer1 }.random()
        val incorrectAnswer3 = (1..10).filter { it != correctAnswer && it != incorrectAnswer1 && it != incorrectAnswer2 }.random()

        val options = listOf(correctAnswer, incorrectAnswer1, incorrectAnswer2, incorrectAnswer3).shuffled()

        // Set the text of each CardView's TextView
        option1CardView.findViewById<TextView>(R.id.textView).text = options[0].toString()
        option2CardView.findViewById<TextView>(R.id.textView).text = options[1].toString()
        option3CardView.findViewById<TextView>(R.id.textView).text = options[2].toString()
        option4CardView.findViewById<TextView>(R.id.textView).text = options[3].toString()

        // Handle cardView clicks
        option1CardView.setOnClickListener {
            handleAnswerClick(options[0], correctAnswer)
        }

        option2CardView.setOnClickListener {
            handleAnswerClick(options[1], correctAnswer)
        }

        option3CardView.setOnClickListener {
            handleAnswerClick(options[2], correctAnswer)
        }

        option4CardView.setOnClickListener {
            handleAnswerClick(options[3], correctAnswer)
        }
    }

    // Function to handle cardView clicks
    private fun handleAnswerClick(selectedAnswer: Int, correctAnswer: Int) {
        if (selectedAnswer == correctAnswer) {
            Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ThirdGameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish() // Finish current activity to prevent going back to this game
        } else {
            moveToTryAgainActivity(2)
            // Perform actions for wrong answer, if needed
        }
    }

    private fun moveToTryAgainActivity(level: Int) {
        val tryAgainIntent = Intent(this, TryAgainActivity::class.java)
        tryAgainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        tryAgainIntent.putExtra("LEVEL", level)
        startActivity(tryAgainIntent)
        finish()
    }
}
