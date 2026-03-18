package com.example.quizappversionone

import java.io.Serializable

data class Score(val id: String = "", val name: String = "", val score: Int = 0) : Serializable {

    constructor() : this("","", 0) // doc.toObject in FirebaseManager needs this
}

