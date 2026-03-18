package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityAnswerQuestionSevenBinding
import java.util.Locale.getDefault

class AnswerQuestionSevenActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnswerQuestionSevenBinding

    lateinit var viewModel: ViewModel
    lateinit var authViewModel: AuthViewModel

    lateinit var currentUsername: String
    lateinit var currentUser: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerQuestionSevenBinding.inflate(layoutInflater)
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
            viewModel.updateAnswersQ7(currentUser, resetAnswer1, answered = false)

            finish()
        }

        var lockInCount = 0
        binding.btnLockInAnswer.setOnClickListener {
            lockInCount++
            binding.btnLockInAnswer.setBackgroundResource(green)

            sendAnswer()

            when (lockInCount) {
                1 -> binding.btnNewQ.visibility = View.VISIBLE
                2 -> binding.btnNewQ.visibility = View.VISIBLE
            }
        }

        var questionCount = 0
        binding.btnNewQ.setOnClickListener {
            val resetAnswer1 = getString(R.string.answer)
            viewModel.updateAnswersQ7(currentUser, resetAnswer1, answered = false)

            questionCount++
            loadNewQuestion(questionCount)

            binding.image.visibility = View.INVISIBLE
            binding.btnNewQ.visibility = View.INVISIBLE
        }


        binding.option1.setOnClickListener {
            updateAnswer(1)
        }

        binding.option2.setOnClickListener {
            updateAnswer(2)
        }


    }

    private fun loadNewQuestion(qCount: Int) {
        val orange = R.drawable.bg_tv_orange

        when (qCount) {
            1 -> loadQuestionTwo(orange)
            2 -> {
                loadQuestionThree(orange)
                binding.btnNewQ.visibility = View.INVISIBLE
            }
        }

    }

    private fun loadQuestionTwo(orange: Int) {

        binding.option1.setBackgroundResource(orange)
        binding.option2.setBackgroundResource(orange)

        binding.tvQuestion.text = getString(R.string.question_7_2)

        binding.option1.text = getString(R.string._plus1)
        binding.option2.text = getString(R.string._plus44)

        binding.selectedAnswer.text = getString(R.string.answer)
        binding.selectedAnswer.setBackgroundResource(orange)

        binding.btnLockInAnswer.setBackgroundResource(orange)
    }

    private fun loadQuestionThree(orange: Int) {

        binding.option1.setBackgroundResource(orange)
        binding.option2.setBackgroundResource(orange)

        binding.tvQuestion.text = getString(R.string.question_7_3)

        binding.option1.text = getString(R.string._sant)
        binding.option2.text = getString(R.string._falskt)

        binding.selectedAnswer.text = getString(R.string.answer)
        binding.selectedAnswer.setBackgroundResource(orange)

        binding.btnLockInAnswer.setBackgroundResource(orange)
    }


    private fun updateAnswer(option: Int) {

        val gray = R.drawable.bg_tv_gray
        val selected = R.drawable.bg_tv_selected

        when (option) {
            1 -> {
                binding.option1.setBackgroundResource(selected)
                binding.option2.setBackgroundResource(gray)

                binding.selectedAnswer.text = binding.option1.text
                binding.selectedAnswer.setBackgroundResource(selected)
            }

            2 -> {
                binding.option1.setBackgroundResource(gray)
                binding.option2.setBackgroundResource(selected)

                binding.selectedAnswer.text = binding.option2.text
                binding.selectedAnswer.setBackgroundResource(selected)

            }

        }

    }

    private fun sendAnswer() {
        val answerOne = binding.selectedAnswer.text.toString().uppercase(getDefault())

        viewModel.updateAnswersQ7(currentUser, answerOne, true)
    }
}