package com.example.quizappversionone

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    private val dataManager = FirebaseManager()

    val users: LiveData<MutableList<User>> = dataManager.users

    val scores: LiveData<MutableList<Score>> = dataManager.scores

    val answersQ1: LiveData<MutableList<AnswerQ1>> = dataManager.answersQ1
    val answersQ2: LiveData<MutableList<AnswerQ2>> = dataManager.answersQ2
    val answersQ3: LiveData<MutableList<AnswerQ3>> = dataManager.answersQ3
    val answersQ4: LiveData<MutableList<AnswerQ4>> = dataManager.answersQ4
    val answersQ5: LiveData<MutableList<AnswerQ5>> = dataManager.answersQ5
    val answersQ6: LiveData<MutableList<AnswerQ6>> = dataManager.answersQ6
    val answersQ7: LiveData<MutableList<AnswerQ7>> = dataManager.answersQ7
    val answersQ8: LiveData<MutableList<AnswerQ8>> = dataManager.answersQ8
    val answersQ9: LiveData<MutableList<AnswerQ9>> = dataManager.answersQ9
    val answersQ10: LiveData<MutableList<AnswerQ10>> = dataManager.answersQ10
    val answersQ12: LiveData<MutableList<AnswerQ12>> = dataManager.answersQ12

    val team1Email = "team1@quiz.se"
    val team2Email = "team2@quiz.se"


    fun addPoint(teamEmail: String) {
        val current = dataManager.getScore(teamEmail)?.score ?: 0
        dataManager.updateScore(teamEmail, current + 1)
    }

    fun addTwoPoint(teamEmail: String) {
        val current = dataManager.getScore(teamEmail)?.score ?: 0
        dataManager.updateScore(teamEmail, current + 2)
    }

    fun checkAnswerAndAwardPoint(
        teamEmail: String,
        teamAnswer: String?,
        correctAnswer: String
    ): Boolean {

        val isCorrect = teamAnswer == correctAnswer

        if (isCorrect) {
            addPoint(teamEmail)
        }

        return isCorrect
    }

    fun updateScore(id: String, score: Int) {
        dataManager.updateScore(id, score)
    }

    fun updateButtonClicked(id: String, buttonClicked: Boolean) {
        dataManager.updateButtonClicked(id, buttonClicked)
    }

    fun updateAnswersQ1(id: String, a1:String, a2:String, a3:String, answered: Boolean) {
        dataManager.updateAnswersQ1(id, a1, a2, a3, answered)
    }
    fun updateAnswersQ2(id: String, a1:String, answered: Boolean) {
        dataManager.updateAnswersQ2(id, a1,answered)
    }

    fun updateAnswersQ3(id: String, a1:String, a2:String, a3:String, a4:String, a5:String, answered: Boolean) {
        dataManager.updateAnswersQ3(id, a1, a2, a3, a4, a5, answered)
    }

    fun updateAnswersQ4(id: String, a1:String, a2:String, a3:String, a4:String, a5:String, a6:String, answered: Boolean) {
        dataManager.updateAnswersQ4(id, a1, a2, a3, a4, a5, a6, answered)
    }

    fun updateAnswersQ5(id: String, low: Int, high: Int, answered: Boolean) {
        dataManager.updateAnswersQ5(id, low, high, answered)
    }

    fun updateAnswersQ6(id: String, a1:String, answered: Boolean) {
        dataManager.updateAnswersQ6(id, a1,answered)
    }

    fun updateAnswersQ7(id: String, a1:String, answered: Boolean) {
        dataManager.updateAnswersQ7(id, a1,answered)
    }

    fun updateAnswersQ8(id: String, a1:String, a2:String, a3:String, answered: Boolean) {
        dataManager.updateAnswersQ8(id, a1, a2, a3, answered)
    }

    fun updateAnswersQ9(id: String, a1:String, a2:String, answered: Boolean) {
        dataManager.updateAnswersQ9(id, a1, a2, answered)
    }

    fun updateAnswersQ10(id: String, a1:String, answered: Boolean) {
        dataManager.updateAnswersQ10(id, a1, answered)
    }

    fun updateAnswersQ12(id: String, a1:String, a2:String, a3:String, a4:String, a5:String, a6:String, a7:String, a8:String, answered: Boolean) {
        dataManager.updateAnswersQ12(id, a1, a2, a3, a4, a5, a6, a7, a8, answered)
    }

    fun getScore(id: String): Score? {

        return dataManager.getScore(id)
    }

//    fun getUser(id: String): User? {
//
//        return dataManager.getUser(id)
//    }

//    fun createUser(name: String, answered: Boolean) {
//        dataManager.createUser(name, answered)
//    }

//    fun getUser(id: String): User? {
//        return dataManager.getUser(id)
//    }
}