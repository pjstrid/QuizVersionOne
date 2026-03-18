package com.example.quizappversionone

import java.io.Serializable

data class AnswerQ10(
    val id: String = "",
    val name: String = "",
    val answered: Boolean = false,
    var answer1: String = ""
) : Serializable {

    constructor() : this("", "",false, "") // doc.toObject in FirebaseManager needs this
}