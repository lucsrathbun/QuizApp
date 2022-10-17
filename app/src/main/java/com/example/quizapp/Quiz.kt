package com.example.quizapp
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import org.w3c.dom.Text
import kotlin.random.Random

class Quiz(val questions: List<Questions>) {
    var totalScore = 0
    var questionsLeft = questions.size
    var index = 0
    var currentQuestion = 0


    fun start(button: Button, displayQuestion: TextView, showScore: TextView, button2: Button, scoreText: String)
    {


            if (checkAnswer(button)) {
                totalScore++
                showScore.text = "$scoreText ${totalScore}"

            }
            currentQuestion++
            questionsLeft--
            if(questionsLeft <= 0) {
                endScore(button, displayQuestion, button2)
                showScore.text = "Your final score is " + totalScore + "/" + currentQuestion
            }
            else {
                index++
                displayQuestion.text = questions[index].question
            }


    }



    private fun checkAnswer(eitherButton: Button): Boolean {
        return questions[index].answer.equals(eitherButton.text)
    }

    public fun endScore(invisibleButton: Button, invisibleQuestion: TextView, invisibleButton2: Button) {
        invisibleButton.isInvisible = true
        invisibleQuestion.isInvisible = true
        invisibleButton2.isInvisible = true

    }



    public fun giveIndex(): Int {
        return index
    }


    // variables to track score, current questions
    //functions to check if there's another question, give the final score, give the next question, check the answer, reset the quiz?, shuffle?
}