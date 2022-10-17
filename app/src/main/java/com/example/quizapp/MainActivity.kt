package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }
    private lateinit var quiz: Quiz
    private lateinit var score: TextView
    public lateinit var buttonFalse: Button
    public lateinit var buttonTrue: Button
    private lateinit var printQuestion: TextView
    private lateinit var questions: List<Questions>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        importQuestions()
        wireWidgets()
        //create the quiz object from the list of questions
        quiz = Quiz(questions)


        //get the first questions, set up the textviews, set up the onclicklisteners for the buttons
        val scoreText = getString(R.string.main_score)
        score.text = "$scoreText ${quiz.totalScore}"
        printQuestion.text = questions[quiz.giveIndex()].question
        buttonTrue.setOnClickListener {
            quiz.start(buttonTrue, printQuestion, score, buttonFalse, scoreText)

        }
        buttonFalse.setOnClickListener {
            quiz.start(buttonFalse, printQuestion, score, buttonTrue, scoreText)
        }
    }

    private fun wireWidgets() {
        score = findViewById(R.id.textView_main_score)
        buttonFalse = findViewById(R.id.button_main_false)
        buttonTrue = findViewById(R.id.button_main_true)
        printQuestion = findViewById(R.id.textView_main_question)
    }

    private fun importQuestions(){
        // step 1: open the raw resource as an InputStream
        // R.raw.questions --> R is the Resources class, raw is folder,
        // questions is the file
        val inputStream = resources.openRawResource(R.raw.questions)
        // step 2: use a buffered reader on the inputstream to read the
        // the text into a string. we call it jsonString because it's the entire JSON file in a string
        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }

        Log.d(TAG, "onCreate: $jsonString")

        val gson = Gson()
        // gson needs to know what kind of list you are converting to
        // typetoken tells gson it will be a List<Question>

        val type = object : TypeToken<List<Questions>>() { }.type
        questions = gson.fromJson<List<Questions>>(jsonString, type)

        Log.d(TAG, "onCreate: $questions")

    }



}