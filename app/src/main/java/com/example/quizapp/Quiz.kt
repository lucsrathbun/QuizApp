package com.example.quizapp

class Quiz(val questions: List<Questions>) {
    var totalScore = 0
    var questionsLeft = 0

    fun start()
    {
        if(questionsLeft == 0) {
            totalScore = 1
        }
    }




    // variables to track score, current questions
    //functions to check if there's another question, give the final score, give the next question, check the answer, reset the quiz?, shuffle?
}