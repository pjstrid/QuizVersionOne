package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityAnswerQuestionFiveBinding

class AnswerQuestionFiveActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnswerQuestionFiveBinding

    lateinit var authViewModel: AuthViewModel

    lateinit var viewModel: ViewModel

    lateinit var currentUsername: String
    lateinit var currentUser: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerQuestionFiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        setArrowsInRoundButtons()

        val orange = R.drawable.bg_tv_orange
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
            val resetAnswer1 = 0
            val resetAnswer2 = 1
            viewModel.updateAnswersQ5(currentUser, resetAnswer1, resetAnswer2, answered = false)

            finish()
        }

        var questionCount = 1
        loadNewQuestion(questionCount)

        binding.btnLockInAnswer.setOnClickListener {
            binding.btnLockInAnswer.setBackgroundResource(green)

            if (questionCount < 3) {
                binding.btnNewQ.visibility = View.VISIBLE
            }

            sendAnswers()
        }

        binding.btnNewQ.setOnClickListener {
            binding.btnNewQ.visibility = View.INVISIBLE
            binding.btnLockInAnswer.setBackgroundResource(orange)

            if (questionCount < 3) {
                questionCount++
            }

            loadNewQuestion(questionCount)
        }

        var btnClicked = 0
        binding.showHidePic.setOnClickListener {
            btnClicked++

            if (btnClicked % 2 == 1) {

                binding.showHidePic.text = getString(R.string.hide_pictures)

                binding.btnClose.visibility = View.INVISIBLE
                binding.pics.visibility = View.VISIBLE
                binding.nextPic.root.visibility = View.VISIBLE

            } else if (btnClicked % 2 == 0) {

                binding.showHidePic.text = getString(R.string.show_pictures)

                binding.pics.visibility = View.INVISIBLE
                binding.previousPic.root.visibility = View.INVISIBLE
                binding.nextPic.root.visibility = View.INVISIBLE

                binding.btnClose.visibility = View.VISIBLE

            }

        }

        var picCount = 1
        binding.previousPic.root.setOnClickListener {
            picCount--
            showPic(picCount)

        }

        binding.nextPic.root.setOnClickListener {
            picCount++
            showPic(picCount)
        }

    }

    private fun showPic(picCount: Int) {

        when (picCount) {
            1 -> {
                binding.pics.setImageResource(R.drawable.house_1)
                binding.previousPic.root.visibility = View.INVISIBLE
            }

            2 -> {
                binding.pics.setImageResource(R.drawable.house_2)
                binding.previousPic.root.visibility = View.VISIBLE
            }

            3 -> binding.pics.setImageResource(R.drawable.house_3)
            4 -> binding.pics.setImageResource(R.drawable.house_4)
            5 -> {
                binding.pics.setImageResource(R.drawable.house_5)
                binding.nextPic.root.visibility = View.VISIBLE
            }

            6 -> {
                binding.pics.setImageResource(R.drawable.house_6)
                binding.nextPic.root.visibility = View.INVISIBLE
            }
        }
    }

    private fun resetAnswers() {

        val resetAnswer1 = 0
        val resetAnswer2 = 1

        binding.etAnswerLow.editText?.text?.clear()
        binding.etAnswerHigh.editText?.text?.clear()

        viewModel.updateAnswersQ5(
            currentUser,
            resetAnswer1,
            resetAnswer2,
            answered = false
        )
    }

    private fun loadNewQuestion(count: Int) {
        resetAnswers()
        val question1 = getString(R.string.question_5_1)
        val question1add = getString(R.string.add_5_1)

        val question2 = getString(R.string.question_5_2)
        val question2add = getString(R.string.add_5_2)

        val question3 = getString(R.string.question_5_3)
        val question3add = getString(R.string.add_5_3)

        when (count) {
            1 -> {
                binding.tvQuestion.text = question1
                binding.tvAddInfo.text = question1add
            }
            2 -> {
                binding.showHidePic.visibility = View.VISIBLE
                binding.tvQuestion.text = question2
                binding.tvAddInfo.text = question2add
            }

            3 -> {
                binding.tvQuestion.text = question3
                binding.tvAddInfo.text = question3add
                binding.showHidePic.visibility = View.INVISIBLE
            }

        }
    }

    private fun sendAnswers() {
        var answerLow = 0
        if (binding.etAnswerLow.editText?.text?.isNotEmpty() == true) {
            answerLow = binding.etAnswerLow.editText?.text.toString().toInt()
        }

        var answerHigh = 0
        if (binding.etAnswerHigh.editText?.text?.isNotEmpty() == true) {
            answerHigh = binding.etAnswerHigh.editText?.text.toString().toInt()
        }

        viewModel.updateAnswersQ5(currentUser, answerLow, answerHigh, true)
    }

    private fun setArrowsInRoundButtons() {
        binding.previousPic.root.text = "<"
        binding.nextPic.root.text = ">"
    }
}