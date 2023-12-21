package com.example.gelamon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ThirdGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_game)

        val closeButton = findViewById<Button>(R.id.closeButton)
        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        val option1Button = findViewById<CardView>(R.id.option1Button)
        val option2Button = findViewById<CardView>(R.id.option2Button)
        val option3Button = findViewById<CardView>(R.id.option3Button)
        val option4Button = findViewById<CardView>(R.id.option4Button)

        // Generate random numbers for addition
        val number1 = (1..10).random()
        val number2 = (1..10).random()
        val sum = number1 + number2

        // Display the addition question
        val question = "What is the sum of $number1 and $number2?"
        questionTextView.text = question

        // Create an array of options including the correct answer
        val options = listOf(sum, (1..20).random(), (1..20).random(), (1..20).random()).shuffled()

        // Set the options on buttons
        option1Button.findViewById<TextView>(R.id.textView).text = options[0].toString()
        option2Button.findViewById<TextView>(R.id.textView).text = options[1].toString()
        option3Button.findViewById<TextView>(R.id.textView).text = options[2].toString()
        option4Button.findViewById<TextView>(R.id.textView).text = options[3].toString()

        // Handle button clicks
        option1Button.setOnClickListener { checkAnswer(options[0], sum) }
        option2Button.setOnClickListener { checkAnswer(options[1], sum) }
        option3Button.setOnClickListener { checkAnswer(options[2], sum) }
        option4Button.setOnClickListener { checkAnswer(options[3], sum) }
    }

    private fun checkAnswer(selectedAnswer: Int, correctAnswer: Int) {
        if (selectedAnswer == correctAnswer) {
            // Correct answer, show congrats message
            Toast.makeText(this, "Congratulations! You answered correctly.", Toast.LENGTH_SHORT).show()
            moveToNextGame()
        } else {
            // Incorrect answer, show a message or handle it as needed
            moveToTryAgainActivity(3)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        }
    }

    private fun moveToNextGame() {
        // Move to the fourth game or perform any action after a correct answer
        startActivity(Intent(this, EndScreenActivity::class.java))
        finish() // Optional: finish this activity to prevent going back to it
    }

    private fun moveToTryAgainActivity(level: Int) {
        val tryAgainIntent = Intent(this, TryAgainActivity::class.java)
        tryAgainIntent.putExtra("LEVEL", level)
        startActivity(tryAgainIntent)
        finish()
    }
}
