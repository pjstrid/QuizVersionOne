package com.example.quizappversionone

import java.io.Serializable

data class AnswerQ12(
    val id: String = "",
    val name: String = "",
    val answered: Boolean = false,
    var answer1: String = "",
    var answer2: String = "",
    var answer3: String = "",
    var answer4: String = "",
    var answer5: String = "",
    var answer6: String = "",
    var answer7: String = "",
    var answer8: String = "",
) : Serializable {

    constructor() : this(
        "",
        "",
        false,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    ) // doc.toObject in FirebaseManager needs this
}
