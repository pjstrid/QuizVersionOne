package com.example.quizappversionone

import java.io.Serializable

data class AnswerQ5(
    val id: String = "",
    val name: String = "",
    val answered: Boolean = false,
    var low: Int = 0,
    var high: Int = 0

) : Serializable {

    constructor() : this("", "", false, 0, 0) // doc.toObject in FirebaseManager needs this
}