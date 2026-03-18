package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionSixBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionSixActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuestionSixBinding
    lateinit var viewModel: ViewModel
    private var team1: AnswerQ6? = null
    private var team2: AnswerQ6? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionSixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]



        binding.btnClose.setOnClickListener {
            finish()
        }

        viewModel.answersQ6.observe(this) { answers ->
            checkAnswered(answers)

            team1 = answers.find { it.name == "TEAM 1" }
            team2 = answers.find { it.name == "TEAM 2" }
        }

        var question = 0

        binding.btnShow.setOnClickListener {
            question++
            binding.tvNumber.visibility = View.VISIBLE

            lifecycleScope.launch {
                binding.tvNumber.text = "3"
                delay(1000)

                binding.tvNumber.text = "2"
                delay(1000)

                binding.tvNumber.text = "1"
                delay(1000)

                binding.tvNumber.visibility = View.INVISIBLE
                updateAnswers(question)
            }
        }


        var qCount = 0
        binding.btnReset.setOnClickListener {
            qCount++
            newQuestion(qCount)
        }

    }

    private fun checkAnswered(answers: List<AnswerQ6>) {

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
            team2?.answered == true
        ) {

            binding.btnShow.visibility = View.VISIBLE

        }
    }

    private fun updateAnswers(question: Int) {

        val greenBg = R.drawable.bg_tv_green
        val redBg = R.drawable.bg_tv_red

        val correctAnswer = when (question) {
            1 -> getString(R.string.arsefacey)
            2 -> getString(R.string.the_teapot)
            3 -> getString(R.string.kingdom_heaven)
            else -> ""
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

        when (question) {
            1 -> {
                binding.option1.setBackgroundResource(redBg)
                binding.option2.setBackgroundResource(greenBg)
                binding.option3.setBackgroundResource(redBg)
                binding.option4.setBackgroundResource(redBg)
            }

            2 -> {
                binding.option1.setBackgroundResource(redBg)
                binding.option2.setBackgroundResource(redBg)
                binding.option3.setBackgroundResource(redBg)
                binding.option4.setBackgroundResource(greenBg)
            }

            3 -> {
                binding.option1.setBackgroundResource(redBg)
                binding.option2.setBackgroundResource(redBg)
                binding.option3.setBackgroundResource(greenBg)
                binding.option4.setBackgroundResource(redBg)
            }
        }
    }

    private fun newQuestion(qCount: Int) {
        val orange = R.drawable.bg_tv_orange

        binding.answer1Team1.text = getString(R.string.answer)
        binding.answer1Team2.text = getString(R.string.answer)

        binding.answer1Team1.setBackgroundResource(orange)
        binding.answer1Team2.setBackgroundResource(orange)

        binding.option1.setBackgroundResource(orange)
        binding.option2.setBackgroundResource(orange)
        binding.option3.setBackgroundResource(orange)
        binding.option4.setBackgroundResource(orange)

        when (qCount) {
            1 -> loadQ2()
            2 -> loadQ3()
        }

    }

    private fun loadQ2() {
        binding.tvQuestion.text = getString(R.string.smeknam_skyskrapa_q6_2)

        binding.option1.text = getString(R.string.the_cheese_grater)
        binding.option2.text = getString(R.string.the_walkie_talkie)
        binding.option3.text = getString(R.string.the_gherkin)
        binding.option4.text = getString(R.string.the_teapot)
    }

    private fun loadQ3() {
        binding.tvQuestion.text = getString(R.string.film_q6_3)

        binding.option1.text = getString(R.string.slumdog)
        binding.option2.text = getString(R.string.dark_knight)
        binding.option3.text = getString(R.string.kingdom_heaven)
        binding.option4.text = getString(R.string.sherlock)
    }

}