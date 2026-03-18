package com.example.quizappversionone

import java.io.Serializable

data class AnswerQ9(
    val id: String = "",
    val name: String = "",
    val answered: Boolean = false,
    var answer1: String = "",
    var answer2: String = ""
) : Serializable {

    constructor() : this("", "",false, "") // doc.toObject in FirebaseManager needs this
}