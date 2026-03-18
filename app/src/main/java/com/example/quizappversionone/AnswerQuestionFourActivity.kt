package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityAnswerQuestionFourBinding

class AnswerQuestionFourActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnswerQuestionFourBinding

    lateinit var authViewModel: AuthViewModel

    lateinit var viewModel: ViewModel

    lateinit var currentUsername: String
    lateinit var currentUser: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerQuestionFourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        viewModel = ViewModelProvider(this)[ViewModel::class.java]


        val gray = R.drawable.bg_tv_gray
        val green = R.drawable.bg_tv_green
        val orange = R.drawable.bg_tv_orange


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
            resetAnswers()

            finish()
        }

        var questionCount = 1
        loadNewQuestion(questionCount)

        binding.btnLockInAnswer.setOnClickListener {
            binding.btnLockInAnswer.setBackgroundResource(green)
            binding.resetSelection.visibility = View.VISIBLE


            if (questionCount < 3) {
                binding.btnNewQ.visibility = View.VISIBLE
            }

            sendAnswers()
        }

        var clickCount = 0

        binding.btnNewQ.setOnClickListener {
            binding.btnNewQ.visibility = View.INVISIBLE
            binding.btnLockInAnswer.setBackgroundResource(orange)
            binding.resetSelection.visibility = View.VISIBLE

            if (questionCount < 3) {
                questionCount++
            }
            clickCount = 0
            resetAnswers()

            loadNewQuestion(questionCount)
        }

        binding.resetSelection.setOnClickListener {
            clickCount = 0
            resetAnswers()
        }

        binding.pairSelection1.setOnClickListener {
            pairChoice(binding.pairSelection1.text.toString(), clickCount)
            clickCount++
            binding.pairSelection1.setBackgroundResource(gray)
        }

        binding.pairSelection2.setOnClickListener {
            pairChoice(binding.pairSelection2.text.toString(), clickCount)
            clickCount++
            binding.pairSelection2.setBackgroundResource(gray)
        }

        binding.pairSelection3.setOnClickListener {
            pairChoice(binding.pairSelection3.text.toString(), clickCount)
            clickCount++
            binding.pairSelection3.setBackgroundResource(gray)
        }

        binding.pairSelection4.setOnClickListener {
            pairChoice(binding.pairSelection4.text.toString(), clickCount)
            clickCount++
            binding.pairSelection4.setBackgroundResource(gray)
        }

        binding.pairSelection5.setOnClickListener {
            pairChoice(binding.pairSelection5.text.toString(), clickCount)
            clickCount++
            binding.pairSelection5.setBackgroundResource(gray)
        }

        binding.pairSelection6.setOnClickListener {
            pairChoice(binding.pairSelection6.text.toString(), clickCount)
            clickCount++
            binding.pairSelection6.setBackgroundResource(gray)
        }

    }

    private fun pairChoice(selection: String, clickCount: Int) {
        val orange = R.drawable.bg_tv_orange

        when (clickCount) {
            0 -> {
                binding.answer1.text = selection
                binding.answer1.setBackgroundResource(orange)
            }

            1 -> {
                binding.answer4.text = selection
                binding.answer4.setBackgroundResource(orange)
            }

            2 -> {
                binding.answer2.text = selection
                binding.answer2.setBackgroundResource(orange)
            }

            3 -> {
                binding.answer5.text = selection
                binding.answer5.setBackgroundResource(orange)
            }

            4 -> {
                binding.answer3.text = selection
                binding.answer3.setBackgroundResource(orange)
            }

            5 -> {
                binding.answer6.text = selection
                binding.answer6.setBackgroundResource(orange)
            }
        }
    }

    private fun loadNewQuestion(count: Int) {
        resetAnswers()
        val sausageRoll = getString(R.string.sausage_roll)
        val toadHole = getString(R.string.toad_in_a_hole)
        val pigBlanket = getString(R.string.pig_in_blanket)

        val korvBacon = getString(R.string.korv_i_bacon)
        val korvSmordeg = getString(R.string.korv_i_smördeg)
        val korvSmet = getString(R.string.korv_i_en_smet)


        val tenderloin = getString(R.string.tenderloin)
        val ribEye = getString(R.string.rib_eye)
        val sirloin = getString(R.string.sirloin)

        val oxefile = getString(R.string.oxfile)
        val entrecote = getString(R.string.entrecote)
        val ryggbiff = getString(R.string.ryggbiff)


        val cricket = getString(R.string.cricket)
        val rugby = getString(R.string.rugby)
        val hockey = getString(R.string.hockey)

        val yorker = getString(R.string.yorker)
        val scrum = getString(R.string.scrum)
        val theD = getString(R.string.the_d)

        when (count) {
            1 -> {
                binding.pairSelection1.text = sausageRoll
                binding.pairSelection2.text = toadHole
                binding.pairSelection3.text = pigBlanket

                binding.pairSelection4.text = korvSmet
                binding.pairSelection5.text = korvBacon
                binding.pairSelection6.text = korvSmordeg
            }

            2 -> {
                binding.pairSelection1.text = tenderloin
                binding.pairSelection2.text = sirloin
                binding.pairSelection3.text = ribEye

                binding.pairSelection4.text = entrecote
                binding.pairSelection5.text = ryggbiff
                binding.pairSelection6.text = oxefile
            }

            3 -> {
                binding.pairSelection1.text = cricket
                binding.pairSelection2.text = hockey
                binding.pairSelection3.text = rugby

                binding.pairSelection4.text = scrum
                binding.pairSelection5.text = theD
                binding.pairSelection6.text = yorker
            }
        }

    }

    private fun resetAnswers() {
        val orange = R.drawable.bg_tv_orange
        val gray = R.drawable.bg_tv_gray

        val resetAnswer1 = getString(R.string.pair_1)
        val resetAnswer2 = getString(R.string.pair_2)
        val resetAnswer3 = getString(R.string.pair_3)

        binding.answer1.text = resetAnswer1
        binding.answer2.text = resetAnswer2
        binding.answer3.text = resetAnswer3

        binding.answer4.text = resetAnswer1
        binding.answer5.text = resetAnswer2
        binding.answer6.text = resetAnswer3

        viewModel.updateAnswersQ4(
            currentUser,
            resetAnswer1,
            resetAnswer2,
            resetAnswer3,
            resetAnswer1,
            resetAnswer2,
            resetAnswer3,
            answered = false
        )

        binding.answer1.setBackgroundResource(gray)
        binding.answer2.setBackgroundResource(gray)
        binding.answer3.setBackgroundResource(gray)
        binding.answer4.setBackgroundResource(gray)
        binding.answer5.setBackgroundResource(gray)
        binding.answer6.setBackgroundResource(gray)

        binding.pairSelection1.setBackgroundResource(orange)
        binding.pairSelection2.setBackgroundResource(orange)
        binding.pairSelection3.setBackgroundResource(orange)
        binding.pairSelection4.setBackgroundResource(orange)
        binding.pairSelection5.setBackgroundResource(orange)
        binding.pairSelection6.setBackgroundResource(orange)

    }

    private fun sendAnswers() {
        val answerOne = binding.answer1.text.toString()
        val answerTwo = binding.answer2.text.toString()
        val answerThree = binding.answer3.text.toString()
        val answerFour = binding.answer4.text.toString()
        val answerFive = binding.answer5.text.toString()
        val answerSix = binding.answer6.text.toString()

        viewModel.updateAnswersQ4(
            currentUser,
            answerOne,
            answerTwo,
            answerThree,
            answerFour,
            answerFive,
            answerSix,
            true
        )
    }
}