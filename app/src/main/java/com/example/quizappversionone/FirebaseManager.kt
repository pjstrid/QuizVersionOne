package com.example.quizappversionone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FirebaseManager {

    private val db = Firebase.firestore

    private val _users = MutableLiveData(mutableListOf<User>())

    val users: LiveData<MutableList<User>> get() = _users

    private val _scores = MutableLiveData(mutableListOf<Score>())

    val scores: LiveData<MutableList<Score>> get() = _scores

    private val _answersQ1 = MutableLiveData(mutableListOf<AnswerQ1>())

    val answersQ1: LiveData<MutableList<AnswerQ1>> get() = _answersQ1

    private val _answersQ2 = MutableLiveData(mutableListOf<AnswerQ2>())

    val answersQ2: LiveData<MutableList<AnswerQ2>> get() = _answersQ2

    private val _answersQ3 = MutableLiveData(mutableListOf<AnswerQ3>())

    val answersQ3: LiveData<MutableList<AnswerQ3>> get() = _answersQ3

    private val _answersQ4 = MutableLiveData(mutableListOf<AnswerQ4>())

    val answersQ4: LiveData<MutableList<AnswerQ4>> get() = _answersQ4

    private val _answersQ5 = MutableLiveData(mutableListOf<AnswerQ5>())

    val answersQ5: LiveData<MutableList<AnswerQ5>> get() = _answersQ5

    private val _answersQ6 = MutableLiveData(mutableListOf<AnswerQ6>())

    val answersQ6: LiveData<MutableList<AnswerQ6>> get() = _answersQ6


    private val _answersQ7 = MutableLiveData(mutableListOf<AnswerQ7>())

    val answersQ7: LiveData<MutableList<AnswerQ7>> get() = _answersQ7

    private val _answersQ8 = MutableLiveData(mutableListOf<AnswerQ8>())

    val answersQ8: LiveData<MutableList<AnswerQ8>> get() = _answersQ8

    private val _answersQ9 = MutableLiveData(mutableListOf<AnswerQ9>())

    val answersQ9: LiveData<MutableList<AnswerQ9>> get() = _answersQ9

    private val _answersQ10 = MutableLiveData(mutableListOf<AnswerQ10>())

    val answersQ10: LiveData<MutableList<AnswerQ10>> get() = _answersQ10

    private val _answersQ12 = MutableLiveData(mutableListOf<AnswerQ12>())

    val answersQ12: LiveData<MutableList<AnswerQ12>> get() = _answersQ12


    init {
        addSnapshotListener()
    }


    fun addSnapshotListener() {

        db.collection("users")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener
                val list = snapshot.toObjects(User::class.java)
                _users.value = list
            }

        db.collection("score")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.documents.map { doc ->
                    doc.toObject(Score::class.java)!!.copy(id = doc.id)
                }

                _scores.value = list.toMutableList()
            }

        db.collection("question1")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ1::class.java)

                _answersQ1.value = list
            }

        db.collection("question2")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ2::class.java)

                _answersQ2.value = list
            }

        db.collection("question3")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ3::class.java)

                _answersQ3.value = list
            }

        db.collection("question4")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ4::class.java)

                _answersQ4.value = list
            }

        db.collection("question5")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ5::class.java)

                _answersQ5.value = list
            }

        db.collection("question6")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ6::class.java)

                _answersQ6.value = list
            }

        db.collection("question7")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ7::class.java)

                _answersQ7.value = list
            }

        db.collection("question8")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ8::class.java)

                _answersQ8.value = list
            }

        db.collection("question9")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ9::class.java)

                _answersQ9.value = list
            }

        db.collection("question10")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ10::class.java)

                _answersQ10.value = list
            }

        db.collection("question12")
            .addSnapshotListener { snapshot, error ->

                if (error != null || snapshot == null) return@addSnapshotListener

                val list = snapshot.toObjects(AnswerQ12::class.java)

                _answersQ12.value = list
            }
    }

    fun updateScore(id: String, score: Int) {

        db.collection("score")
            .document(id).update("score", score)
            .addOnSuccessListener {
                Log.i("!!!", "Updated score on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update score on database, error: " + it.message
                )
            }
    }

    fun updateButtonClicked(id: String, buttonClicked: Boolean) {

        db.collection("users")
            .document(id).update("buttonClicked", buttonClicked)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user buttonClicked on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user buttonClicked on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ1(id: String, a1:String, a2:String, a3:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answer2" to a2,
            "answer3" to a3,
            "answered" to answered
            )

        db.collection("question1")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ2(id: String, a1:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answered" to answered
        )

        db.collection("question2")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ3(id: String, a1:String, a2:String, a3:String, a4:String, a5:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answer2" to a2,
            "answer3" to a3,
            "answer4" to a4,
            "answer5" to a5,
            "answered" to answered
        )

        db.collection("question3")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ4(id: String, a1:String, a2:String, a3:String, a4:String, a5:String, a6:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answer2" to a2,
            "answer3" to a3,
            "answer4" to a4,
            "answer5" to a5,
            "answer6" to a6,
            "answered" to answered
        )

        db.collection("question4")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ5(id: String, low: Int, high: Int, answered: Boolean) {

        val fields = mapOf(
            "low" to low,
            "high" to high,
            "answered" to answered
        )

        db.collection("question5")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ6(id: String, a1:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answered" to answered
        )

        db.collection("question6")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }


    fun updateAnswersQ7(id: String, a1:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answered" to answered
        )

        db.collection("question7")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ8(id: String, a1:String, a2:String, a3:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answer2" to a2,
            "answer3" to a3,
            "answered" to answered
        )

        db.collection("question8")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ9(id: String, a1:String, a2:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answer2" to a2,
            "answered" to answered
        )

        db.collection("question9")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ10(id: String, a1:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answered" to answered
        )

        db.collection("question10")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun updateAnswersQ12(id: String, a1:String, a2:String, a3:String, a4:String, a5:String, a6:String, a7:String, a8:String, answered: Boolean) {

        val fields = mapOf(
            "answer1" to a1,
            "answer2" to a2,
            "answer3" to a3,
            "answer4" to a4,
            "answer5" to a5,
            "answer6" to a6,
            "answer7" to a7,
            "answer8" to a8,
            "answered" to answered
        )

        db.collection("question12")
            .document(id).update(fields)
            .addOnSuccessListener {
                Log.i("!!!", "Updated user answers on database on id: $id")
            }.addOnFailureListener {
                Log.e(
                    "!!!", "Failed to update user answers on database, error: " + it.message
                )
            }
    }

    fun getScore(id: String): Score? {

        return scores.value?.find { it.id == id }
    }

//    fun getUser(id: String): User? {
//
//        return users.value?.find { it.id == id }
//    }

//    fun addSnapshotListener() {
//
//        currentUser = Firebase.auth.currentUser?.email ?: return
//
//        // Add function to listen after changes/interactions
//
//        db.collection("users").document(currentUser)
//            .collection("user").addSnapshotListener { snapshot, error ->
//
//                if (snapshot != null) {
//                    val tempList = mutableListOf<User>()
//
//                    for (doc in snapshot.documents) {
//                        val user = doc.toObject(User::class.java)!!.copy(id = doc.id)
//
//                        tempList.add(user)
//                    }
//                    _users.value = tempList
//                }
//            }
//
//    }


//    fun createUser(name: String, answered: Boolean) {
//
//        currentUser = Firebase.auth.currentUser ?: return
//
//        val fields = mapOf(
//            "name" to name,
//            "answered" to answered
//        )
//
//        db.collection("users").document(currentUser.uid)
//            .collection("user").add(fields).addOnSuccessListener { documentReference ->
//                Log.i("!!!", "Added user to database with id: " + documentReference.id)
//            }.addOnFailureListener {
//                Log.e("!!!", "Failed to add user to database, error: " + it.message)
//            }
//
//    }

}