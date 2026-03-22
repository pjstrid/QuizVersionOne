package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityAnswerQuestionSixBinding
import java.util.Locale.getDefault

class AnswerQuestionSixActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnswerQuestionSixBinding

    lateinit var viewModel: ViewModel
    lateinit var authViewModel: AuthViewModel

    lateinit var currentUsername: String
    lateinit var currentUser : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerQuestionSixBinding.inflate(layoutInflater)
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
            val resetAnswer1 = getString(R.string.answer)
            viewModel.updateAnswersQ6(currentUser, resetAnswer1, answered = false)

            finish()
        }
        var questionCount = 0
        binding.btnLockInAnswer.setOnClickListener {
            binding.btnLockInAnswer.setBackgroundResource(green)

            sendAnswer()

            if (questionCount < 2) {
                binding.btnNewQ.visibility = View.VISIBLE
            }
        }


        binding.btnNewQ.setOnClickListener {
            val resetAnswer1 = getString(R.string.answer)
            viewModel.updateAnswersQ6(currentUser, resetAnswer1, answered = false)

            loadNewQuestion(questionCount)
            questionCount++

            binding.btnNewQ.visibility = View.INVISIBLE
        }



        binding.option1.setOnClickListener {
            updateAnswer(1)
        }

        binding.option2.setOnClickListener {
            updateAnswer(2)
        }

        binding.option3.setOnClickListener {
            updateAnswer(3)
        }

        binding.option4.setOnClickListener {
            updateAnswer(4)
        }

    }
    private fun loadNewQuestion(qCount: Int) {

        val orange = R.drawable.bg_tv_orange

        binding.option1.setBackgroundResource(orange)
        binding.option2.setBackgroundResource(orange)
        binding.option3.setBackgroundResource(orange)
        binding.option4.setBackgroundResource(orange)

        when (qCount) {
            0 -> loadQ2(orange)
            1 -> loadQ3(orange)
        }


    }

    private fun loadQ2(orange: Int) {
        binding.tvQuestion.text = getString(R.string.smeknamn_skyskrapa_q6_2)

        binding.option1.text = getString(R.string.the_cheese_grater)
        binding.option2.text = getString(R.string.the_walkie_talkie)
        binding.option3.text = getString(R.string.the_gherkin)
        binding.option4.text = getString(R.string.the_teapot)

        binding.selectedAnswer.text = getString(R.string.answer)
        binding.selectedAnswer.setBackgroundResource(orange)

        binding.btnLockInAnswer.setBackgroundResource(orange)
    }

    private fun loadQ3(orange: Int) {
        binding.tvQuestion.text = getString(R.string.film_q6_3)

        binding.option1.text = getString(R.string.slumdog)
        binding.option2.text = getString(R.string.dark_knight)
        binding.option3.text = getString(R.string.kingdom_heaven)
        binding.option4.text = getString(R.string.sherlock)

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
                binding.option3.setBackgroundResource(gray)
                binding.option4.setBackgroundResource(gray)

                binding.selectedAnswer.text = binding.option1.text
                binding.selectedAnswer.setBackgroundResource(selected)

            }
            2 -> {
                binding.option1.setBackgroundResource(gray)
                binding.option2.setBackgroundResource(selected)
                binding.option3.setBackgroundResource(gray)
                binding.option4.setBackgroundResource(gray)

                binding.selectedAnswer.text = binding.option2.text
                binding.selectedAnswer.setBackgroundResource(selected)

            }
            3 -> {
                binding.option1.setBackgroundResource(gray)
                binding.option2.setBackgroundResource(gray)
                binding.option3.setBackgroundResource(selected)
                binding.option4.setBackgroundResource(gray)

                binding.selectedAnswer.text = binding.option3.text
                binding.selectedAnswer.setBackgroundResource(selected)

            }
            4 -> {
                binding.option1.setBackgroundResource(gray)
                binding.option2.setBackgroundResource(gray)
                binding.option3.setBackgroundResource(gray)
                binding.option4.setBackgroundResource(selected)

                binding.selectedAnswer.text = binding.option4.text
                binding.selectedAnswer.setBackgroundResource(selected)

            }
        }

    }

    private fun sendAnswer() {
        val answerOne = binding.selectedAnswer.text.toString().uppercase(getDefault())

        viewModel.updateAnswersQ6(currentUser, answerOne, true)
    }
}