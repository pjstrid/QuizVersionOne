package com.example.quizappversionone

import java.io.Serializable

data class User(
    val id: String = "",
    val name: String = "",
    val buttonClicked: Boolean = false
) : Serializable {

    constructor() : this("", "", false) // doc.toObject in FirebaseManager needs this
}
