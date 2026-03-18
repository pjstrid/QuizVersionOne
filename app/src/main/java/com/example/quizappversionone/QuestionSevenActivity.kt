package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionSevenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionSevenActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuestionSevenBinding
    lateinit var viewModel: ViewModel
    private  var team1 : AnswerQ7? = null
    private var team2 : AnswerQ7? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionSevenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        binding.btnClose.setOnClickListener {
            finish()
        }

        viewModel.answersQ7.observe(this) { answers ->
            checkAnswered(answers)

            team1 = answers.find { it.name == "TEAM 1" }
            team2 = answers.find { it.name == "TEAM 2" }
        }

        var qCount = 0
        binding.btnShow.setOnClickListener {
            binding.btnShow.visibility = View.INVISIBLE

            qCount++
            binding.tvNumber.visibility = View.VISIBLE

            lifecycleScope.launch {
                binding.tvNumber.text = "3"
                delay(1000)

                binding.tvNumber.text = "2"
                delay(1000)

                binding.tvNumber.text = "1"
                delay(1000)

                binding.tvNumber.visibility = View.INVISIBLE
                updateAnswers(qCount)
            }
        }

        var qCount2 = 0
        binding.btnReset.setOnClickListener {
            qCount2++
            resetAnswers(qCount2)
        }

    }

    private fun resetAnswers(qCount: Int) {
        val orange = R.drawable.bg_tv_orange

        binding.answer1Team1.text = getString(R.string.answer)
        binding.answer1Team2.text = getString(R.string.answer)
        binding.correctAnswer.text = getString(R.string.correct_answer)

        binding.answer1Team1.setBackgroundResource(orange)
        binding.answer1Team2.setBackgroundResource(orange)

        binding.btnReset.visibility = View.INVISIBLE

        binding.tvQuestion.visibility = View.INVISIBLE
        binding.image.visibility = View.INVISIBLE

        binding.tvQuestion2.visibility = View.VISIBLE

        when (qCount) {
            1 -> binding.tvQuestion2.text = getString(R.string.question_7_2)
            2 -> binding.tvQuestion2.text = getString(R.string.question_7_3)
        }

    }

    private fun checkAnswered(answers: List<AnswerQ7>) {

        val team1 = answers.find { it.name == "TEAM 1" }
        val team2 = answers.find { it.name == "TEAM 2" }

        val green = R.drawable.bg_tv_green
        val red = R.drawable.bg_tv_red

        if (team1?.answered == true) {
            binding.team1Check.setBackgroundResource(green)

        } else {
            binding.team1Check.setBackgroundResource(red)
        }

        if (team2?.answered == true) {
            binding.team2Check.setBackgroundResource(green)

        } else {
            binding.team2Check.setBackgroundResource(red)
        }

        // Show "show-button" when both teams are ready
        if (team1?.answered == true &&
            team2?.answered == true) {

            binding.btnShow.visibility = View.VISIBLE
            binding.btnReset.visibility = View.VISIBLE

        }
    }


    private fun updateAnswers(qCount: Int) {

        val greenBg = R.drawable.bg_tv_green
        val redBg = R.drawable.bg_tv_red
        val correctImg = R.drawable.correct_img
        var correctAnswer = ""

        when (qCount) {
            1 -> {
                correctAnswer = getString(R.string.ethan_hunt)
                binding.image.setImageResource(correctImg)
            }
            2 -> {
                correctAnswer = getString(R.string._plus44)
            }
            3 -> {
                correctAnswer = getString(R.string._sant)
            }
        }

        val team1Answer = team1?.answer1
        val team2Answer = team2?.answer1

        binding.answer1Team1.text = team1Answer
        binding.answer1Team2.text = team2Answer

        val team1Correct = viewModel.checkAnswerAndAwardPoint(
            viewModel.team1Email,
            team1Answer, correctAnswer
        )

        binding.answer1Team1.setBackgroundResource(
            if (team1Correct) greenBg else redBg
        )

        val team2Correct = viewModel.checkAnswerAndAwardPoint(
            viewModel.team2Email,
            team2Answer, correctAnswer
        )

        binding.answer1Team2.setBackgroundResource(
            if (team2Correct) greenBg else redBg
        )

        binding.correctAnswer.text = correctAnswer

    }
}