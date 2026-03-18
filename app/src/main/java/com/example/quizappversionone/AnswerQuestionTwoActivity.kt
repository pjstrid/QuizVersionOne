package com.example.quizappversionone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityAnswerQuestionTwoBinding
import java.util.Locale.getDefault

class AnswerQuestionTwoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnswerQuestionTwoBinding

    lateinit var authViewModel: AuthViewModel

    lateinit var viewModel: ViewModel

    lateinit var currentUsername: String
    lateinit var currentUser: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerQuestionTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val green = R.drawable.bg_tv_green

        when (authViewModel.auth.currentUser?.email) {
            "team1@quiz.se" -> {
                currentUsername = "TEAM 1"
                currentUser = "team1@quiz.se"
            }

            "team2@quiz.se" -> {
                currentUsername = "TEAM 2"
                currentUser = "team2@quiz.se"
            }
        }

        binding.currentUser.text = currentUsername


        binding.btnClose.setOnClickListener {
            val resetAnswer1 = "ANSWER"
            viewModel.updateAnswersQ2(currentUser, resetAnswer1, answered = false)

            finish()
        }

        binding.btnLockInAnswer.setOnClickListener {
            binding.btnLockInAnswer.setBackgroundResource(green)

            sendAnswers()
        }

    }

    private fun sendAnswers() {
        val answerOne = binding.etAnswer1.editText?.text.toString().uppercase(getDefault())

        viewModel.updateAnswersQ2(currentUser, answerOne, true)
    }
}